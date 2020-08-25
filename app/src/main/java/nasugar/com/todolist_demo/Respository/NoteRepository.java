package nasugar.com.todolist_demo.Respository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;

import java.util.List;

import nasugar.com.todolist_demo.DAO.NoteDao;
import nasugar.com.todolist_demo.Database.NoteDatabase;
import nasugar.com.todolist_demo.Entity.Note;

/**
 * Created by Oai.BuiQuoc on 20/08/2020.
 */
public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Context context){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(context);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }


    public void insert(Note note){
        new InsertAsyncTask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteAsyncTask(noteDao).execute(note);
    }

    public void deleteAll(){
        new DeleteAllAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private class InsertAsyncTask extends AsyncTask<Note,Void,Void> {

        private NoteDao noteDao;

        InsertAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        UpdateAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        DeleteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }


    private class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao;

        DeleteAllAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
}
