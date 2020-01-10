/**
 * The app demonstrates screen rotation event in Android.
 *
 * @author Rustam Galimov
 * @version 1.0
 * @since 30.10.2019
 */
package ru.job4j.exam.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ExamBaseHelper extends SQLiteOpenHelper {

    public static final String DB = "exams.db";
    public static final int VERSION = 1;

    private ExamBaseHelper examBaseHelper;

    public ExamBaseHelper(Context context) {
        super(context, DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + ExamDbSchema.ExamTable.NAME
                        + " (" + "id integer primary key autoincrement, "
                        + ExamDbSchema.ExamTable.Cols.TITLE + " "
                        + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    @Singleton
    @Provides
    public SQLiteDatabase getInstance(Context context) {
<<<<<<< HEAD
        return new ExamBaseHelper(context).getWritableDatabase();
=======
        if (examBaseHelper == null) {
            return new ExamBaseHelper(context).getWritableDatabase();
        }
        return examBaseHelper.getWritableDatabase();
>>>>>>> 6f53879857b0522709c08dbd5443d9c52e6069f1
    }
}
