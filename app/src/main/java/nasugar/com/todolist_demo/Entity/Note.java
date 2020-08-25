package nasugar.com.todolist_demo.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Oai.BuiQuoc on 20/08/2020.
 */
@Entity(tableName = "note_table")

public class Note {
    @PrimaryKey (autoGenerate = true)
    int id;

    @ColumnInfo (defaultValue = "")
    String title;

    @ColumnInfo (defaultValue = "")
    String description;

    @ColumnInfo (defaultValue = "")
    int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

}
