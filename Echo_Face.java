//import JavaFX Package
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//import OpenCV Package
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.core.MatOfRect;
import javafx.scene.image.Image;

public class FaceRecognitionApp extends Application {

    private static final String FACE_CASCADE_PATH = "Paste Here"; //download and import xml file from OpenCV GitHub
    private VideoCapture videoCapture;
    private CascadeClassifier faceCascade;
    private ImageView imageView;
    private volatile boolean stopCamera;

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        imageView = new ImageView();
        StackPane imagePane = new StackPane(imageView);

        Label cameraPermissionLabel = new Label("System trying to use camera. Allow?");
        Button allowButton = new Button("Allow");
        Button denyButton = new Button("Deny");

        Button exitButton = new Button("Exit");
        exitButton.setVisible(false);
        exitButton.setOnAction(event -> stopVideoCaptureAndExit());

        allowButton.setOnAction(event -> {
            cameraPermissionLabel.setText("");
            startVideoCapture();

            BorderPane root = (BorderPane) imageView.getScene().getRoot();
            HBox buttonBox = (HBox) root.getTop();
            root.setTop(null);
            root.setBottom(buttonBox);
            exitButton.setVisible(true);
        });

        denyButton.setOnAction(event -> Platform.exit());

        HBox promptBox = new HBox(10, cameraPermissionLabel, allowButton, denyButton);
        promptBox.setStyle("-fx-alignment: center; -fx-padding: 10;");
        HBox exitButtonBox = new HBox(exitButton);

        BorderPane root = new BorderPane();
        root.setCenter(imagePane);
        root.setTop(promptBox);
        root.setBottom(exitButtonBox);

        Scene scene = new Scene(root, 640, 480);
        primaryStage.setTitle("Face Detection");
        primaryStage.setScene(scene);
        primaryStage.show();

        videoCapture = new VideoCapture();
        faceCascade = new CascadeClassifier();

        if (!faceCascade.load(FACE_CASCADE_PATH)) {
            System.err.println("Error: Could not load classifier cascade file");
            return;
        }

        primaryStage.setOnCloseRequest(event -> stopVideoCapture());
    }

    private void startVideoCapture() {
        if (!videoCapture.open(0)) {
            System.err.println("Error: Could not open video capture.");
            return;
        }

        stopCamera = false;

        new Thread(() -> {
            Mat frame = new Mat();
            while (!stopCamera) {
                if (videoCapture.read(frame)) {
                    Mat grayFrame = new Mat();
                    Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
                    detectFaces(grayFrame, frame);

                    Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2BGRA);
                    updateImageView(frame);
                }
            }
        }).start();
    }

    private void detectFaces(Mat grayFrame, Mat frame) {
        MatOfRect faces = new MatOfRect();
        faceCascade.detectMultiScale(grayFrame, faces);

        for (Rect face : faces.toArray()) {
            Imgproc.rectangle(frame, face.tl(), face.br(), new Scalar(0, 255, 0), 3);
        }
    }

    private void updateImageView(Mat frame) {
        Image image = Utils.mat2Image(frame);
        Platform.runLater(() -> imageView.setImage(image));
    }

    private void stopVideoCapture() {
        stopCamera = true;
        if (videoCapture.isOpened()) {
            videoCapture.release();
        }
    }

    private void stopVideoCaptureAndExit() {
        stopVideoCapture();
        Platform.exit();
    }
}
