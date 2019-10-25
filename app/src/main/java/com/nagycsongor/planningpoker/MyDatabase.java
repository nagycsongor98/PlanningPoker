package com.nagycsongor.planningpoker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static final String TAG = "MyDatabase";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "my.db";

    private static final String USERS = "USERS";
    private static final String USERS_ID = "ID";
    private static final String USERS_NAME = "USERS_NAME";

    private static final String PROBLEMS = "PROBLEMS";
    private static final String PROBLEMS_ID = "ID";
    private static final String PROBLEMS_NAME = "PROBLEMS_NAME";

    private static final String VOTE = "VOTE";
    private static final String VOTE_ID = "ID";
    private static final String VOTE_USER_ID = "USER_ID";
    private static final String VOTE_PROBLEM_ID = "PROBLEM_ID";
    private static final String VOTE_TICKET = "TICKET";
    private static final String VOTE_VOTED = "VOTED";

    private int user_id;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        insertProblems();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create USERS table.

        String sql = "CREATE TABLE " + USERS + " (" +
                USERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USERS_NAME + " TEXT)";
        db.execSQL(sql);

        //Create PROBLEMS table and insert values.

        sql = "CREATE TABLE " + PROBLEMS + " (" +
                PROBLEMS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PROBLEMS_NAME + " TEXT )";
        db.execSQL(sql);


        //Create VOTE table.

        sql = "CREATE TABLE " + VOTE + " (" +
                VOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                VOTE_USER_ID + " INTEGER ," +
                VOTE_PROBLEM_ID + " INTEGER," +
                VOTE_TICKET + " INTEGER," +
                VOTE_VOTED + " INTEGER," +
                "constraint vote_fk foreign key (" + VOTE_USER_ID +") references " + USERS + "(" + USERS_ID +  ")," +
                "constraint vote_fk1 foreign key (" + VOTE_PROBLEM_ID +") references " + PROBLEMS + "(" + PROBLEMS_ID +  "))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Insert values to PROBLEMS table.
    private void insertProblems(){
        insertProblem("LogIn");
        insertProblem("Design");
        insertProblem("HomeFragment");
        insertProblem("ListFragment");
        insertProblem("Database");
    }

    //Insert a new value to PROBLEMS table.
    private void insertProblem(String problem){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor problems =  db.rawQuery( "SELECT * FROM " + PROBLEMS + " WHERE " + PROBLEMS_NAME + " = '" + problem +"'" , null );
        if (problems.getCount()<1){
            ContentValues values = new ContentValues();
            values.put(PROBLEMS_NAME,problem);

            db.insert(PROBLEMS,null,values);
        }

        db.close();
    }

    //Insert a new user to USERS table if not listed in the table and set his votes.
    public void insertUser(String user_name){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor user =  db.rawQuery( "SELECT * FROM " + USERS + " WHERE " + USERS_NAME + " = '" + user_name +"'", null );

        if(user.getCount()<1){

            //Insert to users table
            ContentValues values = new ContentValues();
            values.put(USERS_NAME,user_name);
            db.insert(USERS,null,values);

            //Get user id
            user =  db.rawQuery( "SELECT * FROM " + USERS + " WHERE " + USERS_NAME + " = '" + user_name +"'", null );
            user.moveToFirst();
            this.user_id = user.getInt(user.getColumnIndex(USERS_ID));

            Log.i(TAG, "Insert a new user, user_id: " + String.valueOf(user_id));

            //Inset votes to user
            insertVotesToUser();

        }else{
            //Get user id
            user.moveToFirst();
            this.user_id = user.getInt(user.getColumnIndex(USERS_ID));
            Log.i(TAG, "User: " + String.valueOf(user_id) + " exist already");
        }
        db.close();

    }

    //Connect the new user to database and set your id.
    public void connectUser(String user_name){
        SQLiteDatabase db = this.getWritableDatabase();

        //Get user id
        Cursor user =  db.rawQuery( "SELECT * FROM " + USERS + " WHERE " + USERS_NAME + " = '" + user_name +"'", null );
        user.moveToFirst();
        this.user_id = user.getInt(user.getColumnIndex(USERS_ID));
        Log.i(TAG, "User:" + user_name + " id: " + String.valueOf(user_id));
        db.close();

    }

    //Insert votes to a  new user into Vote table.
    private void insertVotesToUser(){
        Log.i(TAG, "Insert votes to user: " + String.valueOf(user_id));
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor problems =  db.rawQuery( "SELECT * FROM " + PROBLEMS , null );
        problems.moveToFirst();
        while(problems.isAfterLast() == false){

            int problem_id = problems.getInt(problems.getColumnIndex(PROBLEMS_ID));
            ContentValues values = new ContentValues();
            values.put(VOTE_USER_ID, user_id);
            values.put(VOTE_PROBLEM_ID,problem_id);
            values.put(VOTE_VOTED,0);
            db.insert(VOTE,null,values);
            problems.moveToNext();
        }
        db.close();

    }

    //Return a new vote  if exist, otherwise an empty string.
    public String getNewVote(){
        String voteTo = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor votes =  db.rawQuery( "SELECT * " +
                "FROM " + VOTE +
                " WHERE " + VOTE_USER_ID + " = '" + user_id +
                "' AND " + VOTE_VOTED + " = " + 0, null );
        if (votes.getCount()<1){
            voteTo = "";
        }else {
            votes.moveToFirst();
            int problem_id = votes.getInt(votes.getColumnIndex(VOTE_PROBLEM_ID));
            Cursor problem =  db.rawQuery( "SELECT * " +
                    "FROM " + PROBLEMS +
                    " WHERE " + PROBLEMS_ID + " = " + problem_id, null );
            problem.moveToFirst();
            voteTo = problem.getString(problem.getColumnIndex(PROBLEMS_NAME));
            Log.i(TAG,"Vote to: " + voteTo);
        }
        db.close();
        return voteTo;
    }

    //Update in the VOTE table the problem name voted column to 1.
    public void votedTo(String problem_name , int ticket){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor problem =  db.rawQuery( "SELECT * " +
                "FROM " + PROBLEMS +
                " WHERE " + PROBLEMS_NAME + " = '" + problem_name + "'", null );
        problem.moveToFirst();
        int problem_id = problem.getInt(problem.getColumnIndex(PROBLEMS_ID));
        ContentValues update = new ContentValues();
        update.put(VOTE_TICKET,ticket);
        update.put(VOTE_VOTED,1);
        db.update(VOTE,update,VOTE_USER_ID + " = " + user_id + " AND " + VOTE_PROBLEM_ID + " = " + problem_id,null);


        db.close();
    }

    public ArrayList<String> getVotes (String problem_name){
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();

        //Get problem id.
        Cursor problem =  db.rawQuery( "SELECT * " +
                "FROM " + PROBLEMS +
                " WHERE " + PROBLEMS_NAME + " = '" + problem_name + "'", null );
        problem.moveToFirst();
        int problem_id = problem.getInt(problem.getColumnIndex(PROBLEMS_ID));

        Cursor votes =  db.rawQuery( "SELECT * " +
                "FROM " + VOTE +
                " WHERE " + VOTE_PROBLEM_ID + " = '" + problem_id + "' " +
                "AND " + VOTE_VOTED + " = " + 1, null );

        votes.moveToFirst();
        while(votes.isAfterLast() == false){

            //Get user name.
            Cursor user =  db.rawQuery( "SELECT * " +
                    "FROM " + USERS +
                    " WHERE " + USERS_ID + " = " + votes.getInt(votes.getColumnIndex(VOTE_USER_ID)), null );
            user.moveToFirst();
            String user_name = user.getString(user.getColumnIndex(USERS_NAME));


            String string = user_name + " " +  votes.getInt(votes.getColumnIndex(VOTE_TICKET));
            array_list.add(string);
            votes.moveToNext();
        }

        db.close();
        return array_list;
    }


}
