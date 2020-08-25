package nasugar.com.todolist_demo.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import nasugar.com.todolist_demo.DAO.NoteDao;
import nasugar.com.todolist_demo.Entity.Note;

/**
 * Created by Oai.BuiQuoc on 20/08/2020.
 */
@Database(entities = Note.class,version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static NoteDatabase instance;

    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder( context, NoteDatabase.class, "note_database" )
                    .fallbackToDestructiveMigration()
                    .addCallback( roomCallback )
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private PopulateDbAsyncTask(NoteDatabase noteDatabase){
            this.noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1","Description 1",1));
            noteDao.insert(new Note("Title 2","Description 3",2));
            noteDao.insert(new Note("Title 3","Description 3",3));
            return null;
        }
    }
}
