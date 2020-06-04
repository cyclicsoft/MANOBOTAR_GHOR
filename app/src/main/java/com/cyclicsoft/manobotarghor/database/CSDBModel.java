/**
 * @author SHIPON
 * Base class for all database adapters
 */
package com.cyclicsoft.manobotarghor.database;

import android.content.Context;
import android.database.Cursor;
import com.cyclicsoft.manobotarghor.appfw.CSBaseModel;
import com.cyclicsoft.manobotarghor.appfw.CSEntity;
import com.cyclicsoft.manobotarghor.appfw.NotifyObserver;
import com.cyclicsoft.manobotarghor.appfw.ResponseObject;

import java.util.List;

// <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
public abstract class CSDBModel extends CSBaseModel {
    public static final String ID = "id";
    protected DatabaseController mDatabaseController;

    public CSDBModel(Context context) {
        super(context);
        mDatabaseController = DatabaseController.getInstance();
    }

    /**
     * Load the data from cursor
     *
     * @param cursor
     */
    protected abstract void loadData(Cursor cursor);

    /**
     * Insert the entity
     *
     * @param entity
     * @return
     */
    abstract public long insert(CSEntity entity);

    // Insert list of entity using different thread. This notifyObserver for observer is posted using Handler.
    public void insertListAsync(final List<CSEntity> entityList, final NotifyObserver observer) {
        this.setNotifyObserver(observer);
        final ResponseObject response = new ResponseObject();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    for (CSEntity entity : entityList) {
                        long res = ((CSDBModel) mInstance).insert(entity);
                        response.setResponseMsg("Continue");
                    }
                    response.setResponseMsg("SuccessfulinsertListAsyncMessage");
                    mInstance.notifyObserver(response);
                } catch (Exception e) {
                    response.setResponseMsg(e.getMessage());
                    // Error: Need to handle
                    mInstance.notifyObserver(response);
                }
            }
        }).start();
    }

    /**
     * Retrieve all information from database in thread. This notifyObserver for observer is posted using Handler
     */
    public ResponseObject queryAsync(NotifyObserver observer) {
        this.setNotifyObserver(observer);
        final ResponseObject response = new ResponseObject();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ((CSDBModel) mInstance).query();
                    response.setResponseMsg("SuccesfulQueryMessage");
                    mInstance.notifyObserver(response);
                } catch (Exception e) {
                    response.setResponseMsg(e.getMessage());
                    // Error: Need to handle
                    mInstance.notifyObserver(response);
                }
            }
        }).start();

        return null;
    }

    /**
     * Update the entity
     *
     * @param entity
     * @return
     */
    abstract public int update(CSEntity entity);

    /**
     * Delete the entity
     * // * @param entity
     *
     * @return
     */
    public int delete(int id, String table) {
        String[] whereArgs = new String[]{DatabaseController.toString(id)};
        String where = ID + "=?";
        int row = mDatabaseController.delete(table, where, whereArgs);
        return row;
    }

    /**
     * Query the database
     *
     * @return
     */
    abstract public void query();

    /**
     * @param table
     * @return
     */
    public int clearTable(String table) {
        int row = mDatabaseController.delete(table, "", null);
        return row;
    }
}

