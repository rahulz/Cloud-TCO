package com.cloudbia.cloudcompare.contentProvider;

import java.io.IOException;

import com.cloudbia.cloudcompare.MySQLiteHelper;
import com.cloudbia.cloudcompare.Plan;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;


import android.net.Uri;

public class LocalDbProvider extends ContentProvider {
    public LocalDbProvider() {
    }
    private MySQLiteHelper dbHelper;
    private static final int PLANS = 1;
    private static final int PLANS_RAW = 2;
    private static final int PLANS_RAW_GOOGLE=3;
    private static final int PLANS_RAW_AMAZON=4;
    private static final int PLANS_RAW_AMAZON_EBS=8;
    private static final int PLANS_RAW_HP=5;
    private static final int PLANS_RAW_AZURE=6;
    private static final int PLANS_RAW_RACKSPACE=7;
    // authority is the symbolic name of your provider
    // To avoid conflicts with other providers, you should use
    // Internet domain ownership (in reverse) as the basis of your provider authority.
    private static final String AUTHORITY = "com.cloudbia.cloudcompare.contentProvider";
    // create content URIs from the authority by appending path to database table
    public static final Uri PLAN_URI=
            Uri.parse("content://" + AUTHORITY + "/plan");
    public static final Uri PLAN_URI_RAW=
            Uri.parse("content://" + AUTHORITY + "/plan_raw");
    public static final Uri PLAN_URI_RAW_GOOGLE=
            Uri.parse("content://" + AUTHORITY + "/plan_raw_google");
    public static final Uri PLAN_URI_RAW_AMAZON=
            Uri.parse("content://" + AUTHORITY + "/plan_raw_amazon");
    public static final Uri PLAN_URI_RAW_AMAZON_EBS=
            Uri.parse("content://" + AUTHORITY + "/plan_raw_amazon_ebs");
    public static final Uri PLAN_URI_RAW_HP=
            Uri.parse("content://" + AUTHORITY + "/plan_raw_hp");
    public static final Uri PLAN_URI_RAW_AZURE=
            Uri.parse("content://" + AUTHORITY + "/plan_raw_azure");
    public static final Uri PLAN_URI_RAW_RACKSPACE=
            Uri.parse("content://" + AUTHORITY + "/plan_raw_rackspace");
 
    // a content URI pattern matches content URIs using wildcard characters:
    // *: Matches a string of any valid characters of any length.
    // #: Matches a string of numeric characters of any length.
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "plan", PLANS);
        uriMatcher.addURI(AUTHORITY, "plan_raw", PLANS_RAW);
        uriMatcher.addURI(AUTHORITY, "plan_raw_google", PLANS_RAW_GOOGLE);
        uriMatcher.addURI(AUTHORITY, "plan_raw_hp", PLANS_RAW_HP);
        uriMatcher.addURI(AUTHORITY, "plan_raw_amazon", PLANS_RAW_AMAZON);
        uriMatcher.addURI(AUTHORITY, "plan_raw_amazon_ebs", PLANS_RAW_AMAZON_EBS);
        uriMatcher.addURI(AUTHORITY, "plan_raw_azure", PLANS_RAW_AZURE);
        uriMatcher.addURI(AUTHORITY, "plan_raw_rackspace", PLANS_RAW_RACKSPACE);
        
    }
    // system calls onCreate() when it starts up the provider.
    @Override
    public boolean onCreate() {
        // get access to the database helper
    	try {
    		dbHelper = new MySQLiteHelper(getContext());
            dbHelper.createDataBase();
		} catch (SQLiteException e) {
			e.printStackTrace();
		}
    	catch (IOException e) {
    		e.printStackTrace();
		}
        
        
        return false;
    }
    //Return the MIME type corresponding to a content URI
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case PLANS:
                return "vnd.android.cursor.dir/vnd.com.sha.netstager.pandafoods.contentprovider.plans";
             default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
    // The insert() method adds a new row to the appropriate table, using the values
    // in the ContentValues argument. If a column name is not in the ContentValues argument,
    // you may want to provide a default value for it either in your provider code or in
    // your database schema.
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case PLANS:
                id = db.insert(Plan.SQLITE_TABLE, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return Uri.parse(PLAN_URI +"/"+ id);
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
    // The query() method must return a Cursor object, or if it fails,
    // throw an Exception. If you are using an SQLite database as your data storage,
    // you can simply return the Cursor returned by one of the query() methods of the
    // SQLiteDatabase class. If the query does not match any rows, you should return a
    // Cursor instance whose getCount() method returns 0. You should return null only
    // if an internal error occurred during the query process.
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
    	Cursor cursor=null;
        switch (uriMatcher.match(uri)) {
            case PLANS:
                //do nothing
                queryBuilder.setTables(Plan.SQLITE_TABLE);
                break;
            case PLANS_RAW:
            	cursor=db.rawQuery("SELECT * FROM data where provider=? AND cpu>=? AND memory>=? AND hdd>=? AND os=? AND Price=(SELECT MIN(price) FROM data where provider=? AND cpu>=? AND memory>=? AND hdd>=? AND os=?)", selectionArgs);
            	return cursor;
            case PLANS_RAW_GOOGLE:
            	cursor=db.rawQuery("SELECT * FROM data where provider=? AND cpu>=? AND memory>=? AND Price=(SELECT MIN(price) FROM data where provider=? AND cpu>=? AND memory>=?)", selectionArgs);
            	return cursor;
            case PLANS_RAW_AMAZON:
            	cursor=db.rawQuery("SELECT * FROM data where provider=? AND cpu>=? AND memory>=? AND hdd>=? AND os=? AND Price=(SELECT MIN(price) FROM data where provider=? AND cpu>=? AND memory>=? AND hdd>=? AND os=?)", selectionArgs);
            	return cursor;
            case PLANS_RAW_AMAZON_EBS:
            	cursor=db.rawQuery("SELECT * FROM data where provider=? AND cpu>=? AND memory>=? AND os=? AND hdd=? AND Price=(SELECT MIN(price) FROM data where provider=? AND cpu>=? AND memory>=? AND os=? AND hdd=?)", selectionArgs);
            	return cursor;            	
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        return queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
    }
    // The delete() method deletes rows based on the seletion or if an id is
    // provided then it deleted a single row. The methods returns the numbers
    // of records delete from the database. If you choose not to delete the data
    // physically then just update a flag here.
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleteCount;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case PLANS:
                //do nothing
                deleteCount = db.delete(Plan.SQLITE_TABLE, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return deleteCount;
    }
    // The update method() is same as delete() which updates multiple rows
    // based on the selection or a single row if the row id is provided. The
    // update method returns the number of updated rows.
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int updateCount = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case PLANS:
                //do nothing
                updateCount= db.update(Plan.SQLITE_TABLE, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return updateCount;
    }
}