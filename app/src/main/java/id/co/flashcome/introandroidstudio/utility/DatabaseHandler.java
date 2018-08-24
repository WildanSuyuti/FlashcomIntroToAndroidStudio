package id.co.flashcome.introandroidstudio.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.co.flashcome.introandroidstudio.model.User;

/**
 * Created by kakaroto on 23/08/18.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private final String TAG = DatabaseHandler.class.getSimpleName();
    private static final int DATABASE_VERSION = 5;
    private static final String
            DATABASE_NAME = "database",
            TABLE_USERS = "users",
            KEY_ID = "id",
            KEY_NAMA = "nama",
            KEY_EMAIL = "email", KEY_PASSWORD = "password";

    private static SQLiteDatabase db;
    private static DatabaseHandler instance;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static void init(Context context) {
        instance = new DatabaseHandler(context);
        db = instance.getWritableDatabase();
    }

    public static synchronized DatabaseHandler getInstance() {
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAMA + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, user.getNama());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());

        db.insert(TABLE_USERS, null, values);
        Log.d(TAG, "Add data Succes !");
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setNama(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        return userList;
    }

    public void updateUser(User user) {

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, user.getNama());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());
        Log.d(TAG, "Update Succes !");

        db.update(TABLE_USERS, values, KEY_ID + " = '" + user.getId() + "'", null);
    }

    public void deleteUser(User user) {
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        Log.d(TAG, "Delete Succes !");
    }

    public User getUser(int id) {
        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_ID,
                        KEY_NAMA, KEY_EMAIL, KEY_PASSWORD}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return new User(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
    }

    public boolean checkUser(String email, String pass) {
        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_EMAIL + " = '" + email
                + "' AND " + KEY_PASSWORD + " = '" + pass + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.d(TAG, "checkUser: " + cursor);
        return cursor.moveToFirst();
    }

    public User getUser(String email) {
        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_EMAIL + " = '" + email
                + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        return new User(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
    }

}
