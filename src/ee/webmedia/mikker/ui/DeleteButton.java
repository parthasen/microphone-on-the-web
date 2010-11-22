package ee.webmedia.mikker.ui;

import ee.webmedia.mikker.Recorder;
import ee.webmedia.mikker.events.RecordingEvent;
import ee.webmedia.mikker.events.RecordingListener;

import javax.swing.*;

public class DeleteButton extends JButton implements RecordingListener {
    private Recorder recorder;

    public DeleteButton(Recorder soundRecorder) {
        super(new Icons().getDeleteIcon());
        this.recorder = soundRecorder;

        setEnabled(false);
    }
    
    public void onRecordingEvent(RecordingEvent event) {
        setEnabled(event.isRecordingAvailable());
    }
}