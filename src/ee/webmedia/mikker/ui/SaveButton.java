package ee.webmedia.mikker.ui;

import ee.webmedia.mikker.Recorder;
import ee.webmedia.mikker.events.RecordingEvent;
import ee.webmedia.mikker.events.RecordingListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveButton extends JButton implements ActionListener, RecordingListener {
    private Recorder recorder;

    public SaveButton(Recorder recorder) {
        super(new Icons().getSaveIcon());
        this.recorder = recorder;
        addActionListener(this);
        setEnabled(false);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            FileOutputStream out = new FileOutputStream("result.au");
            recorder.writeResult(out);
            System.out.println("Wrote file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRecordingEvent(RecordingEvent event) {
        setEnabled(event.isRecordingAvailable());
    }
}