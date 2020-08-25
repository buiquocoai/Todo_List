package nasugar.com.todolist_demo.ViewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

import nasugar.com.todolist_demo.Entity.Note;
import nasugar.com.todolist_demo.NoteActivity;
import nasugar.com.todolist_demo.Respository.NoteRepository;

/**
 * Created by Oai.BuiQuoc on 20/08/2020.
 */
public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super( application );
//        Log.d( "BBB", "NoteViewModel: " );
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }


    public void insert(Note note) {
        noteRepository.insert(note);
    }

    public void update(Note note) {
        noteRepository.update(note);
    }

    public void delete(Note note) {
        noteRepository.delete(note);
    }

    public void deleteAll() {
        noteRepository.deleteAll();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
