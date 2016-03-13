package com.codepandablog.coherence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.tangosol.net.cache.CacheStore;
import com.tangosol.util.Base;

public class DBCacheStore extends Base implements CacheStore {

	protected Connection conn;
	protected String msTableName;
	private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String DB_USERNAME = "hr";
	private static final String DB_PASSWORD = "hr";

	public DBCacheStore(String sTableName) {
		msTableName = sTableName;
		configureConnection();
	}

	protected void configureConnection() {
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager
					.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			conn.setAutoCommit(true);
		} catch (Exception e) {
			throw ensureRuntimeException(e, "Connection failed");
		}
	}

	public Object load(Object oKey) {
		Object oValue = null;
		Connection con = getConnection();
		String sSQL = "SELECT id, value FROM " + getTableName()
				+ " WHERE id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sSQL);
			stmt.setString(1, String.valueOf(oKey));
			ResultSet rslt = stmt.executeQuery();
			if (rslt.next()) {
				oValue = rslt.getString(2);
				if (rslt.next()) {
					throw new SQLException("Not a unique key: " + oKey);
				}
			}
			stmt.close();
		} catch (SQLException e) {
			throw ensureRuntimeException(e, "Load failed: key=" + oKey);
		}
		return oValue;
	}

	public String getTableName() {
		return msTableName;
	}

	public Connection getConnection() {
		return conn;
	}

	public DBCacheStore() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map loadAll(Collection arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void erase(Object oKey) {
		Connection con = getConnection();
		String sSQL = "DELETE FROM " + getTableName() + " WHERE id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sSQL);
			stmt.setString(1, String.valueOf(oKey));
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw ensureRuntimeException(e, "Erase failed: key=" + oKey);
		}

	}

	@Override
	public void eraseAll(Collection arg0) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void store(Object oKey, Object oValue) {
		Connection con = getConnection();
		String sTable = getTableName();
		String sSQL;
		if (load(oKey) != null) {
			sSQL = "UPDATE " + sTable + " SET value = ? where id = ?";
		} else {
			sSQL = "INSERT INTO " + sTable + " (value, id) VALUES (?,?)";
		}
		try {
			PreparedStatement stmt = con.prepareStatement(sSQL);
			int i = 0;
			stmt.setString(++i, String.valueOf(oValue));
			stmt.setString(++i, String.valueOf(oKey));
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw ensureRuntimeException(e, "Store failed: key=" + oKey);
		}
	}

	@Override
	public void storeAll(Map arg0) {
		throw new UnsupportedOperationException();
	}

	public Iterator keys() {
		Connection con = getConnection();
		String sSQL = "SELECT id FROM " + getTableName();
		List list = new LinkedList();
		try {
			PreparedStatement stmt = con.prepareStatement(sSQL);
			ResultSet rslt = stmt.executeQuery();
			while (rslt.next()) {
				Object oKey = rslt.getString(1);
				list.add(oKey);
			}
			stmt.close();
		} catch (SQLException e) {
			throw ensureRuntimeException(e, "Iterator failed");
		}
		return list.iterator();
	}
}
