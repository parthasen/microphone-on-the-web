package ee.webmedia.mikker.ui;

import ee.webmedia.mikker.recorder.SoundRecorder;
import ee.webmedia.mikker.upload.Configuration;
import ee.webmedia.mikker.upload.Uploader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecorderPanel extends JPanel {
    private final int MAX_RECORDING_DURATION = 2 * 60 * 1000;

    public RecorderPanel(Configuration ctx) {
        final SoundRecorder soundRecorder = new SoundRecorder(ctx.getFilename());
        
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel buttonBar = new JPanel();
        buttonBar.setBackground(Color.WHITE);
        
        add(buttonBar, BorderLayout.NORTH);

        RecordButton recordButton = new RecordButton(soundRecorder, MAX_RECORDING_DURATION);
        soundRecorder.addListener(recordButton);
        buttonBar.add(recordButton);

        DeleteButton deleteButton = new DeleteButton();
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                soundRecorder.deleteRecording();
                RecorderPanel.this.transferFocus();
            }
        });

        soundRecorder.addListener(deleteButton);
        buttonBar.add(deleteButton);


        Uploader uploader = new Uploader(ctx);

        SaveButton saveButton = new SaveButton(soundRecorder, uploader);
        soundRecorder.addListener(saveButton);
        buttonBar.add(saveButton);

        FilenameField filename = new FilenameField(ctx);
        soundRecorder.addListener(filename);

        add(filename, BorderLayout.SOUTH);
    }
}
