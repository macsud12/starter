package com.macs.starter.storage;

import com.macs.starter.model.Note;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maksim_Alipov.
 */
@Component
public class NoteHolder {
    private HashMap<String, List<Note>> notes = new HashMap<String, List<Note>>();


    public void save(String username, Note note) {
        if (!notes.containsKey(username)) {
            notes.put(username, new LinkedList<Note>());
        }
        notes.get(username).add(note);
    }

    public List<Note> getAll(String username) {
        return notes.get(username);
    }

    public boolean delete(String username, String noteId){
        if (!notes.containsKey(username)) {
            return false;
        }
        for (Iterator<Note> it=notes.get(username).iterator(); it.hasNext();) {
            if (it.next().getId().equals(noteId)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean update(String username, String noteId, String content){
        if (!notes.containsKey(username)) {
            return false;
        }
        for (Iterator<Note> it=notes.get(username).iterator(); it.hasNext();) {
            Note note = it.next();
            if (note.getId().equals(noteId)) {
                note.setContent(content);
                return true;
            }
        }
        return false;
    }
}
