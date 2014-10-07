package com.smartroom.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smartroom.model.FilterPreferenceModel;

public class DBHelper {

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final String DATABASE_NAME = "SmartRoom.db";
	private static final String TABLE_NAME = "preference";
	private static final int DATABASE_VERSION = 1;
	private static FilterPreferenceModel _preferences = null;

	private static final String CREATE_PREFERENCE_TABLE = "CREATE TABLE IF NOT EXISTS `"
			+ TABLE_NAME
			+ "` ("
			+ "`minPrice` varchar(100) DEFAULT NULL,"
			+ "`maxPrice` varchar(100) DEFAULT NULL,"
			+ "`minBed` varchar(100) DEFAULT NULL,"
			+ "`maxBed` varchar(100) DEFAULT NULL,"
			+ "`sellerType` varchar(100) DEFAULT NULL,"
			+ "`propertyType` varchar(100) DEFAULT NULL,"
			+ "`searchRange` varchar(100) DEFAULT NULL,"
			+ "`addedDate` varchar(100) DEFAULT NULL,"
			+ "`sortBy` varchar(100) DEFAULT NULL,"
			+ "`priceFrequency` varchar(100) DEFAULT NULL,"
			+ "`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP);";

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_PREFERENCE_TABLE);
			db.execSQL("INSERT INTO "
					+ TABLE_NAME
					+ "(sellerType, propertyType, addedDate, sortBy, priceFrequency) values ('Any','Any','Last 24 Hours',"
					+ "'Most Recent First','Per Calendar Month')");
		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}

	public void Reset() {
		mDbHelper.onUpgrade(this.mDb, 1, 1);
	}

	public DBHelper(Context ctx) {
		mCtx = ctx;
		mDbHelper = new DatabaseHelper(mCtx);
	}

	public DBHelper open() throws SQLException {
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	public void clearCustomersTable() {
		mDb.execSQL("delete from " + TABLE_NAME);
	}

	public void dropCustomersTable() {
		mDb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	}

	public void updateCustDetails(FilterPreferenceModel preference) {

		String query = "UPDATE " + TABLE_NAME + " set minPrice = '"
				+ preference.getMinPrice() + "'," + "maxPrice='"
				+ preference.getMaxPrice() + "'," + "minBed='"
				+ preference.getMinBed() + "'," + "maxBed='"
				+ preference.getMaxBed() + "'," + "sellerType='"
				+ preference.getSellerType() + "'," + "propertyType='"
				+ preference.getPropertyType() + "'," + "searchRange='"
				+ preference.getSearchDistance() + "'," + "addedDate='"
				+ preference.getAddedDate() + "'," + "sortBy='"
				+ preference.getSortBy() + "'," + "priceFrequency='"
				+ preference.getPriceFrequency() + "'";

		mDb.execSQL(query);
	}

	public FilterPreferenceModel retrivePreferences() throws SQLException {
		Cursor cur = mDb.query(true, "preference", new String[] { "minPrice",
				"maxPrice", "minBed", "maxBed", "sellerType", "propertyType",
				"searchRange", "addedDate", "sortBy", "priceFrequency" }, null,
				null, null, null, null, null);

		_preferences = new FilterPreferenceModel();

		if (cur.moveToNext()) {
			_preferences.setAddedDate(cur.getString(cur
					.getColumnIndex("addedDate")));
			_preferences.setMaxBed(cur.getString(cur.getColumnIndex("maxBed")));
			_preferences.setMaxPrice(cur.getString(cur
					.getColumnIndex("maxPrice")));
			_preferences.setMinBed(cur.getString(cur.getColumnIndex("minBed")));
			_preferences.setMinPrice(cur.getString(cur
					.getColumnIndex("minPrice")));
			_preferences.setPriceFrequency(cur.getString(cur
					.getColumnIndex("priceFrequency")));
			_preferences.setPropertyType(cur.getString(cur
					.getColumnIndex("propertyType")));
			_preferences.setSearchDistance(cur.getString(cur
					.getColumnIndex("searchRange")));
			_preferences.setSellerType(cur.getString(cur
					.getColumnIndex("sellerType")));
			_preferences.setSortBy(cur.getString(cur.getColumnIndex("sortBy")));

			return _preferences;
		}
		return null;
	}
}
