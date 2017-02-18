
package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.lang.model.type.NullType;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import math.PhaseMath;

/**
 * @author Nathan
 * @see PhaseDiagramControler.Swing 
 * @version 2.0
 *
 * <h1>What it Does</h1>
 * <p>This Application is responsible for creating the parameter that is responsible for controlling and handling a phase diagram,Although the platform
 * is constructed around the <i>Iron-IronCarbide</i> diagram it works for all 2d phase diagrams if it follows the proper instruction in creating the file.</p>
 * @see Instructions in creating the Metal Maker files.
 */
public class PhaseDiagram_Controler extends AnchorPane implements Initializable {


	public String point1_info = "Something about POINT 1";
	public String point2_info = "Something about POINT 2";
	public String point3_info = "Something about POINT 3";
	public String point4_info = "Something about POINT 4";
	public String point5_info = "Something about POINT 5";
	public String point6_info = "Something about POINT 6";
	public String point7_info = "Something about POINT 7";
	public String point8_info = "Something about POINT 8";
	
//
	// Linked nodes from the .fxml file
	
	@FXML public AnchorPane mainHolder;
	
	@FXML public ImageView layerAImageView;
	@FXML public ImageView layerBImageView;
	
	@FXML public Label timeDisplayer;				
	@FXML public Label errorMessage_lb;
	
	@FXML public Label phaseName_click;
	@FXML public Label purePhase_click;
	@FXML public Label rightFraction_click;
	@FXML public Label leftFraction_click;
	
	@FXML public Label centigrade_move;
	@FXML public Label farhanite_move;
	@FXML public Label ironComp_move;
	@FXML public Label cemmentiteComp_move;
	@FXML public Label phaseName_move;
	@FXML public Label carbon_comp_move;
	
	
	//Main Radio Toggle buttons MW(Mass Weight),AW(Atomic Weight)
	@FXML public RadioButton comp_mw_rb;
	@FXML public RadioButton comp_aw_rb;
	
	@FXML public RadioButton carbon_mw_rb;
	@FXML public RadioButton cemmentite_mw_rb;
	@FXML public RadioButton iron_mw_rb;
	
	@FXML public RadioButton carbon_aw_rb;
	@FXML public RadioButton cemmentite_aw_rb;
	@FXML public RadioButton iron_aw_rb;
	
	@FXML public RadioButton centigrade_rb;
	@FXML public RadioButton farhanite_rb;

	// Text Fields(tf) for the respective toggle Buttons up there.
	@FXML public TextField carbon_mw_tf;
	@FXML public TextField cemmentite_mw_tf;
	@FXML public TextField iron_mw_tf;
	
	@FXML public TextField carbon_aw_tf;
	@FXML public TextField cemmentite_aw_tf;
	@FXML public TextField iron_aw_tf;
	
	//Temperature text Fields
	@FXML public TextField centigrade_tf;
	@FXML public TextField farhanite_tf;
	
	//Buttons
	@FXML public Button calcFrac_but;
	@FXML public Button export_but;
	
	// Progress Indicators,Used however to show the current temp and composition cursor position
	
	@FXML public ProgressBar comp_progIndic;
	@FXML public ProgressBar temp_progIndic;
	@FXML public ProgressBar carbon_progIndic;
	
	@FXML public AnchorPane theDrawingPane;
	@FXML public ComboBox<String> uniquePoints;
	
	@FXML public MenuBar theMenu;
	
	@FXML public TreeTableView<TableValues> tableView = new TreeTableView<>();
	
	@FXML public TreeTableColumn<TableValues, String> points_column;
	@FXML public TreeTableColumn<TableValues, String> cemmComp_column;
	@FXML public TreeTableColumn<TableValues, String> temp_column;
	@FXML public TreeTableColumn<TableValues, String> note_column;
	//New Added Columns
	@FXML public TreeTableColumn<TableValues, String> isOnPurePhase_column;
	@FXML public TreeTableColumn<TableValues, String> onPhaseName_column;
	@FXML public TreeTableColumn<TableValues, String> rightFraction_column;
	@FXML public TreeTableColumn<TableValues, String> leftFraction_column;
	@FXML public TreeTableColumn<TableValues, String> coolingTime_column;
	
	@FXML public AnchorPane treeTableHolder;
	
	@FXML public Label rightFractionName;
	@FXML public Label leftFractionName;
	
	// File Menu items 
	@FXML public MenuItem open_MI;
	@FXML public MenuItem save_MI;
	@FXML public MenuItem saveAs_MI;
	@FXML public MenuItem exit_MI;
	// Edit Menu items
	@FXML public MenuItem clearAllNative_MI;
	@FXML public MenuItem clearAllCustom_MI;
	@FXML public MenuItem showAllNative_MI;
	@FXML public MenuItem clearAll_MI;
	// View Menu items
	@FXML public MenuItem zoomIn_MI;
	@FXML public MenuItem zoomOut_MI;
	@FXML public MenuItem pan_MI;
	@FXML public MenuItem select_MI;
	// Help Menu items
	@FXML public MenuItem aboutUs_MI;
	@FXML public MenuItem contactUs_MI;
	@FXML public MenuItem visitPage_MI;
	@FXML public MenuItem whatAM_MI;
	@FXML public MenuItem credit_MI;
	// Exported Values to ParentValue Displayer
	@FXML public  TextArea userNote;
	@FXML public  Label leftElement;
	@FXML public  Label rightElement;
	@FXML public  TextArea phaseDescription;
	@FXML public  Label elementFormulas;
	
	public String dataName = "Default Name";
	
	PhaseMath orgPoint = new PhaseMath();
	int radius = 3;
	
	Circle native1538 = new Circle(6,31,radius);
	Circle native1493 = new Circle(39,54,radius);
	Circle native1394 = new Circle(4,103,radius);
	Circle native912  = new Circle(6, 344,radius);
	
	Circle native430  = new Circle(orgPoint.comp_To_mouseX(false, 64.18,0),orgPoint.temp_To_mouseY(false, 1147, 0),radius);
	
	Circle native214  = new Circle(325, 226,radius);
	Circle native076  = new Circle(114,436,radius);
	Circle native002  = new Circle(15,436,radius);
	
	// Color Code values for phases
	public final static int LIQUID_CODE = -1236956;
	public final static int DELTA_LIQUID_CODE = -9384740;
	public final static int DELTA_CODE = -959956;
	public final static int DELTA_AUSTENITE_CODE = -7485886;
	public final static int AUSTENITE_LIQUID_CODE = -1;
	public final static int LIQUID_CEMMENTITE_CODE = -857322;
	public final static int AUSTENITE_CODE = -13085788;
	public final static int AUSTENITE_CEMMENTITE_CODE = -4697441;
	public final static int FERRITE_AUSTENITE_CODE = -9089751;
	public final static int FERRITE_CODE = -15767239;
	public final static int FERRITE_CEMMENTITE_CODE = -11447983;
	public final static int CEMMENTITE_CODE = -12337492;
	public final static int IRON_CODE = -8496730;
	
	
	//all Components under MassWeight Group
	ArrayList<Node> mw_Components = new ArrayList<>();
	ArrayList<Node> aw_Components = new ArrayList<>();
	//list of Components to be disabled when the program starts
	ArrayList<Node> default_disable = new ArrayList<>();
		
	public PixelReader pxReader;
		
	// Values to EXPORT to the main Working Environment
		public SimpleStringProperty phaseName = new SimpleStringProperty("Default Name");
		public String isPurePhase;
		public double rightFrac = 0;
		public double leftFrac = 0;
		//Values changes for mouse clicked
		public double cemmentiteComp = 0;
		public double ironComp = 0;
		public double tempCent = 0;
		public double tempFar = 0;
		
		public String noteOnPoint = "Add note Here";
	//Error Message
		public static String errorMessage = "No error Message";
	// Values changes for mouse moved mouse dragged
		public SimpleStringProperty current_phaseName = new SimpleStringProperty();;
		public double current_cementiteComp = 0;
		public double current_ironComp = 0;
		public double current_tempCent = 0;
		public double current_tempFar = 0;
	//
	
	//FOR PHASE FRACTION CALCULATION
	/**
	 * Color Code for Mixed Phases.	
	 */
	final private int[] mixedPhase = {-9384995,-7485885,-1,-4697441,-9089751,-11447983,-9384740,-7485886,-857322,};
	/**
	 * Color Code for Pure phases.
	 */
	final private int[] purePhases = {-1236700,-959956,-15701703,-1236956,-13085788,-15767239,-12337492,};
	
//	private int[] points = {-4211200,-3156992,-4222720,-1775360,-4325632,-2560,-3080427,-16777216};
	
	private Line clickedLine1;
	private Line clickedLine2;
	private Line movedLine1;
	private Line movedLine2;
	private Rectangle r1;
	
	private int markerRX = 0;
	private int markerLX = 0;
	private int markerCurrentX = 0;
	public  int mouseX;
	public int mouseY;
	public boolean isPure;
		
	//Note the the label name are ridiculous change them to a more conventional names
	//For Unique Temprature points designator labels
	public ObservableList<String> uniquePoints_List = FXCollections.observableArrayList("1538C Point","1493C Point","1394C Point","912C Point","4.30% Point","2.14% Point","0.76% Point","0.02% Point");	
	//Variables associated with Lingering other info is declared just after the class declaration
	
	public TextArea info_AboutArea = new TextArea("Default Info");
	public VBox infoAreaContainer = new VBox();
	public Label infoAreaName = new Label("default Name");
	
	private Thread t1;
	public boolean haveLingered = false;
	public double mouseX_Linger;
	public double mouseY_Linger;
	
	public double infoArea_size_X = 250;
	public double infoArea_size_Y = 150;
	
	int timeLine = 0;
	
	ContextMenu popupWindow = new ContextMenu();
	
	// Working Space pop up menu items
	MenuItem newDiagram = new MenuItem("New Diagram",new ImageView(new Image("newDiagram.png",20,20,true,true)));
	MenuItem zoomIn = new MenuItem("Zoom In", new ImageView(new Image("zoomIn.png")));
	MenuItem zoomOut = new MenuItem("Zoom Out",new ImageView(new Image("zoomOut.png")));
	MenuItem addMarker = new MenuItem("Add Marker",new ImageView(new Image("add.png",20,20,true,true)));
	MenuItem removeMarker = new MenuItem("Remove Marker",new ImageView(new Image("remove.png",20,20,true,true)));
	MenuItem removeAll = new MenuItem("Remove All Markers");
	MenuItem removeAllCustom = new MenuItem("Remove All Custom Markers");

	MenuItem removeAllNative = new MenuItem("Remove All Native Markers");
	MenuItem showNativeMarkers = new MenuItem("Show Native Markers");
	MenuItem closeSpace = new MenuItem("Close Working-Space");
	MenuItem pan = new MenuItem("Pan",new ImageView(new Image("grab_cursor.png",20,20,true,true)));
	MenuItem select = new MenuItem("Select",new ImageView(new Image("select.png",20,20,true,true)));
	
	ArrayList<String> customPoints = new ArrayList<>();
	TableValues nativeValue = new TableValues("Native Points");
	TableValues customValue = new TableValues("Custom Points");
	
	//Composition values,Names,and notes are place holders replace with correct values 
	
	TreeItem<TableValues> tableCustomPoints = new TreeItem<>(customValue);
	
	ArrayList<Circle> nativeCircles = new ArrayList<>();
	ArrayList<Rectangle> customRectangles = new ArrayList<>();
	
	ArrayList<Shape> geometryList = new ArrayList<>();
	
	HashMap<Integer, String> customPointNotes = new HashMap<>();
	
	private static final String CLICKED = "clicked";
	private static final String MOVED = "moved";
	PhaseMath phaseMath = new PhaseMath();
	
	public ContextMenu popMenuTable = new ContextMenu();
	public MenuItem removeElementTable = new MenuItem("remove element");
	public MenuItem removeAllCustomTable = new MenuItem("remove all custom marks");
	
	public Image imageASmall = new Image("layerASmall.png", 1000, 600,true, true);
	public Image imageALarge = new Image("layerALarge.png", 2000, 1200,true, true);
	
	public Image imageBSmall = new Image("layerBSmall.png", 1000, 600, true, true);
	public Image imageBLarge = new Image("layerBLarge.png", 2000, 1200, true, true);
	
	int viewPortX = 0;
	int viewPortY = 0;
	Rectangle2D viewPortRectangle = new Rectangle2D(viewPortX, viewPortY, 1000, 600);
	
	int changeX= 0;
	int changeY= 0;
	
	int initX = 0;
	int initY = 0;
	boolean isZoom = false;
	
	ArrayList<Double[]> initialCoordinateNative = new ArrayList<>();
	ArrayList<Double[]> initialCoordinateCustom = new ArrayList<>();
	
	Rectangle visibleArea;
	
	public FileChooser fileDialog = new FileChooser();
	
	
/**
 * <h1>Description</h1>
 * Constructor Method.
 */
	public PhaseDiagram_Controler(){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PhaseDiagram_GUI.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * <h1>Description</h1>
 * Defines pixelReader,ToggleGroups, Disables ToggleGropus by default, and Sets the Current time as well. 
 * @see #markNative_UniquePoints()
 * @see #treeTableConstructor_nativePoints()
 * @see #items_initializedStatus()
 * @see #timeLine_Counter(int)
 * @see #addNativePoints_CustomizeCircles
 * @see #manualInput_initializedState
 * @see #programs_initializedStates
 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		markNative_UniquePoints();
		treeTableConstructor_nativePoints();
		items_initializedStatus();
		timeLine_Counter(1000);
		addNativePoints_CustomizeCircles();
		manualInput_initializedState();
		programs_initializedStates();
		treeTablePopUpinitialize();
		pointsInitialPosition();
		pointPlacer();
		
	}
	
	public void onMenuItemAction(ActionEvent e){
		//Actions for File Menu items
		if (e.getSource() == open_MI) {
			openFile();
		}
		else if (e.getSource() == save_MI) {
			saveFile();
		}
		else if (e.getSource() == saveAs_MI) {
			saveAsFile();
		}
		else if (e.getSource() == exit_MI) {
			closeSpace();
		}
		// Actions for Edit Menu Items
		else if (e.getSource() == clearAllNative_MI) {
			removeAllNative(theDrawingPane);
		}
		else if (e.getSource() == clearAllCustom_MI) {
			removeAllCustom(theDrawingPane);
		}
		else if (e.getSource() == showAllNative_MI) {
			showNativeMarkers();
		}
		else if (e.getSource() == clearAll_MI) {
			removeAllMarkers(theDrawingPane);
		}
		// Actions for View Menu Items
		else if (e.getSource() == zoomIn_MI) {
			onZoomIn();
		}
		else if (e.getSource() == zoomOut_MI) {
			onZoomOut();
		}
		else if (e.getSource() == pan_MI) {
			panAction();
		}
		else if (e.getSource() == select_MI) {
			selectAction();
		}
		// Actions for Help Menu items
		else if (e.getSource() == aboutUs_MI) {
			System.out.println("AboutUs Selected");
		}
		else if (e.getSource() == contactUs_MI) {
			System.out.println("ContactUs Selected");
		}
		else if (e.getSource() == visitPage_MI) {
			System.out.println("VisitPage Selected");
		}
		else if (e.getSource() == whatAM_MI) {
			System.out.println("What is AM Selected");
		}
		else if (e.getSource() == credit_MI) {
			System.out.println("Credit Selected");
		}
		
	}
	@SuppressWarnings("unused")
	public void saveAsFile() {
		fileDialog.setTitle("Save As");
		fileDialog.setInitialDirectory(new File("C:\\Users\\Nathan\\Documents\\Eclipse Mars,Neon Working Environment\\MetalMaker_PhaseDiagram\\savedFiles"));
		fileDialog.getExtensionFilters().add(new ExtensionFilter("Alloy Property Calculator files", "*.apc"));
		fileDialog.setInitialFileName("Untitled.am");
		File file = fileDialog.showSaveDialog(Main.secondaryStage); // TODO  use file instance
	}
	@SuppressWarnings("unused")
	public void saveFile() {
		fileDialog.setTitle("Save");
		fileDialog.setInitialDirectory(new File("C:\\Users\\Nathan\\Documents\\Eclipse Mars,Neon Working Environment\\MetalMaker_PhaseDiagram\\savedFiles"));
		fileDialog.getExtensionFilters().add(new ExtensionFilter("Alloy Property Calculator files", "*.apc"));
		fileDialog.setInitialFileName("Untitled.am");
		File file = fileDialog.showSaveDialog(Main.secondaryStage); // TODO  use file instance
	}
	@SuppressWarnings("unused")
	public void openFile() {
		fileDialog.setTitle("Open");
		fileDialog.setInitialDirectory(new File("C:\\Users\\Nathan\\Documents\\Eclipse Mars,Neon Working Environment\\MetalMaker_PhaseDiagram\\savedFiles"));
		fileDialog.getExtensionFilters().add(new ExtensionFilter("Alloy Property Calculator files", "*.apc"));
		File file = fileDialog.showOpenDialog(Main.secondaryStage);
		//TODO Insert and define file reader here
	}
	
	private void pointsInitialPosition(){
		for (int i = 0; i < nativeCircles.size(); i++) {
			initialCoordinateNative.add(i,new Double[] {nativeCircles.get(i).getCenterX(),nativeCircles.get(i).getCenterY()});
		}
	}

	public void pointPlacer() {
		visibleArea = new Rectangle(viewPortX, viewPortY, 1000, 600);
		
		for (int i = 0; i < nativeCircles.size(); i++) {
			nativeCircles.get(i).setVisible(true);
			double originalXPosition = initialCoordinateNative.get(i)[0];
			double originalYPosition = initialCoordinateNative.get(i)[1];
			
			if (visibleArea.contains(originalXPosition*2, originalYPosition*2) && isZoom ) {
				nativeCircles.get(i).setCenterX((originalXPosition*2)-viewPortX);
				nativeCircles.get(i).setCenterY((originalYPosition*2)-viewPortY);
				
			}
			else if (visibleArea.contains(originalXPosition*2, originalYPosition*2)==false && isZoom) {
				nativeCircles.get(i).setVisible(false);
			}
			else if (isZoom == false) {
				nativeCircles.get(i).setCenterX(originalXPosition);
				nativeCircles.get(i).setCenterY(originalYPosition);
			}
			
		}
		for (int i = 0; i < customRectangles.size(); i++) {
			customRectangles.get(i).setVisible(true);
			double originalXPosition = initialCoordinateCustom.get(i)[0];
			double originalYPosition = initialCoordinateCustom.get(i)[1];
			

			if (visibleArea.contains(originalXPosition*2, originalYPosition*2) && isZoom ) {
				customRectangles.get(i).setX((originalXPosition*2)-viewPortX);
				customRectangles.get(i).setY((originalYPosition*2)-viewPortY);
				
			}
			else if (visibleArea.contains(originalXPosition*2, originalYPosition*2)==false && isZoom) {
				customRectangles.get(i).setVisible(false);
			}
			else if (isZoom == false) {
				customRectangles.get(i).setX(originalXPosition);
				customRectangles.get(i).setY(originalYPosition);
			}
		}
		
		for (int i = 0; i < theDrawingPane.getChildren().size(); i++) {
			Node node = theDrawingPane.getChildren().get(i);
			if ((node instanceof Circle) && (node.getId() == null)) {
				Circle c = (Circle) node;
				c.setVisible(true);
				
				double originalXPosition = c.getCenterX();
				double originalYPosition = c.getCenterY();

				if (!(visibleArea.contains(originalXPosition+viewPortX, originalYPosition+viewPortY)) && isZoom) {
					c.setVisible(false);
				}
				else if (isZoom == false) {
					c.setCenterX(originalXPosition);
					c.setCenterY(originalYPosition);
				}
			}
			else if ((node instanceof Line) && node.getId() != "compIndicators" && node.getId() != "xMarker") {
				Line l = (Line)node;
				l.setVisible(true);
				
				double originalXPosition = l.getStartX();
				double originalYPosition = l.getStartY();

				if (!(visibleArea.contains(originalXPosition+viewPortX, originalYPosition+viewPortY)) && isZoom) {
					l.setVisible(false);
				}
				else if (isZoom == false) {
					l.setStartX(originalXPosition);
					l.setStartY(originalYPosition);
				}
			}
			else if (node instanceof Text) {
				Text t = (Text)theDrawingPane.getChildren().get(i);
				t.setVisible(true);
				
				double originalXPosition = t.getX();
				double originalYPosition = t.getY();

				if (!(visibleArea.contains(originalXPosition+viewPortX, originalYPosition+viewPortY)) && isZoom) {
					t.setVisible(false);
				}
				else if (isZoom == false) {
					t.setX(originalXPosition);
					t.setY(originalYPosition);
				}
			}
		}
	}
	private double clamp(double value, double min, double max) {
		if (value < min) {
			return min;
		} else if (value > max) {
		return max;
		}
		return value;
	}
	public void onZoomOut(){
		viewPortX = 0;
		viewPortY = 0;
		//Changing Disable status of popUp Menu Items
		pan.setDisable(true);
		zoomIn.setDisable(false);
		//Changing Disable status of MenuItem Edit
		zoomOut_MI.setDisable(true);
		pan_MI.setDisable(true);
		select_MI.setDisable(false);
		zoomIn_MI.setDisable(false);
		
		targetRemoverById(new String[]{"nativeCircles","customRectangles","crossLine"},theDrawingPane,true);
		isZoom = false;
		imageASmall = new Image("layerASmall.png", 1000, 600, true, true);
		layerAImageView.setImage(imageASmall);
		layerAImageView.setViewport(new Rectangle2D(0,0, 1000, 600));
		
		pxReader = layerAImageView.getImage().getPixelReader();
		
		layerAImageView.setOnMouseDragged(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// Cancel the original onMouseDragged Method by doing nothing
			}
		});
		imageBSmall = new Image("layerBsmall.png", 1000, 600, true, true);
		layerBImageView.setImage(imageBSmall);
		layerBImageView.setViewport(new Rectangle2D(0,0, 1000, 600));
		layerBImageView.setOnMouseDragged(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// Cancel the original onMouseDragged Method by doing nothing
			}
		});
		pointPlacer();
		zoomOut.setDisable(true);
	}
	public void onZoomIn(){
		//Changing Disable status of popUp Menu Items
		pan.setDisable(false);
		zoomOut.setDisable(false);
		zoomIn.setDisable(true);
		zoomOut.setDisable(false);
		//Changing Disable status of MenuItem Edit
		zoomIn_MI.setDisable(true);
		zoomOut_MI.setDisable(false);
		pan_MI.setDisable(false);
		
		targetRemoverById(new String[]{"nativeCircles","customRectangles"},theDrawingPane,true);
		isZoom = true;
		
		layerAImageView.setImage(imageALarge);
		layerAImageView.setCursor(Cursor.OPEN_HAND);
		layerAImageView.setViewport(viewPortRectangle);
		
		layerBImageView.setImage(imageBLarge);
		layerBImageView.setCursor(Cursor.MOVE);
		layerBImageView.setViewport(viewPortRectangle);
		
		pxReader = layerAImageView.getImage().getPixelReader();
		pointPlacer();
	}
/**
 * <h1>Description</h1>
 * Mostly Unrelated methods that are initialized at the begining of the program.
 * @return {@link Void}
 */
	private void programs_initializedStates() {
		pan.setDisable(true);
		zoomOut.setDisable(true);
		layerAImageView.setImage(imageASmall);
		layerBImageView.setImage(imageBSmall);
		
		layerAImageView.setViewport(viewPortRectangle);
		layerBImageView.setViewport(viewPortRectangle);
		
		
		infoAreaContainer.getChildren().addAll(infoAreaName,info_AboutArea);
		info_AboutArea.setMaxSize(infoArea_size_X, infoArea_size_Y);
		info_AboutArea.setEditable(false);
	
		uniquePoints.setItems(uniquePoints_List);
		
		pxReader = layerAImageView.getImage().getPixelReader();
		
		Date date = new Date();
		timeDisplayer.setText(date.toString());
		errorMessage_lb.setText(errorMessage);
	}
/**
 * <h1>Description</h1>
 * Manual Input working pane initialized state corresponding to the radio buttons state
 * @return {@link Void}
 */
	private void manualInput_initializedState() {
		
		ToggleGroup alphaGroup = new ToggleGroup();
		comp_mw_rb.setToggleGroup(alphaGroup);
		comp_aw_rb.setToggleGroup(alphaGroup);
	
		ToggleGroup mw_group = new ToggleGroup();
		carbon_mw_rb.setToggleGroup(mw_group);
		cemmentite_mw_rb.setToggleGroup(mw_group);
		iron_mw_rb.setToggleGroup(mw_group);
	
		ToggleGroup at_group = new ToggleGroup();
		carbon_aw_rb.setToggleGroup(at_group);
		cemmentite_aw_rb.setToggleGroup(at_group);
		iron_aw_rb.setToggleGroup(at_group);
	
		ToggleGroup temp_group = new ToggleGroup();
		centigrade_rb.setToggleGroup(temp_group);
		farhanite_rb.setToggleGroup(temp_group);
	
		mw_Components.add(carbon_mw_rb);mw_Components.add(carbon_mw_tf); mw_Components.add(cemmentite_mw_rb); mw_Components.add(cemmentite_mw_tf);
		mw_Components.add(iron_mw_rb);mw_Components.add(iron_mw_tf);
		
		aw_Components.add(carbon_aw_rb);aw_Components.add(carbon_aw_tf);aw_Components.add(cemmentite_aw_rb);aw_Components.add(cemmentite_aw_tf);
		aw_Components.add(iron_aw_rb);aw_Components.add(iron_aw_tf);
	
		default_disable.add(carbon_mw_tf);default_disable.add(iron_mw_tf);default_disable.add(carbon_aw_tf);default_disable.add(iron_aw_tf);
		default_disable.add(farhanite_tf);
	
		for (int i = 0; i < aw_Components.size(); i++) {
		aw_Components.get(i).setDisable(true);
		}
		for (int i = 0; i < default_disable.size(); i++) {
		default_disable.get(i).setDisable(true);
		}
}
/** 
 * <h1>Description</h1>
 * Adds Collection of native points to an ArrayList.
 * @return {@link Void}
 */
	private void addNativePoints_CustomizeCircles() {
		nativeCircles.addAll(FXCollections.observableArrayList(native1538,native1493,native1394,native912,native002,native076,native214,native430));
		for (int i = 0; i < nativeCircles.size(); i++) {
			nativeCircles.get(i).setStroke(Color.BLACK);
			nativeCircles.get(i).setFill(Color.RED);
			nativeCircles.get(i).setId("nativeCircles");
			nativeCircles.get(i).setRadius(4.0f);
			nativeCircles.get(i).setSmooth(true);
			nativeCircles.get(i).setBlendMode(BlendMode.OVERLAY);

			geometryList.add(nativeCircles.get(i));
		}
	}
/**
 * <h1>Description</h1>
 * Method checks wether the specified geometry collection contains the mouse Co-ordinate points
 * and alterantes between {@link #markerMovedResponse(Shape)} ( a response when the mouse hovers on any geometry)
 * or {@link #toNormalState(ArrayList)} ( which is a normal condition.
 * @param event {@link MouseEvent}
 * @param shapeCollection {@link ArrayList}
 */
	private Shape containsGeometry(MouseEvent event,ArrayList<Shape> shapeCollection,Color color){
	
		boolean b = false;
		for (int i = 0; i < shapeCollection.size(); i++) {
			if (shapeCollection.get(i).contains(event.getX(), event.getY())& color!= null) {
				geometrySelectionEffect(shapeCollection.get(i),color,MOVED);
				b = true;
				return shapeCollection.get(i);
			}
			else if (shapeCollection.get(i).contains(event.getX(), event.getY()) & color == null) {
				return shapeCollection.get(i);
			}
		}
		if (b==false) {
			toNormalState(shapeCollection,null);
		}
		return null;
		
	}
	public boolean isCoordinateContainedInGeometry(int xCoOrdinate,int yCoOrdinate,ArrayList<Shape> shapeCollection){
		boolean b = false;
		for (int i = 0; i < shapeCollection.size(); i++) {
			if (shapeCollection.get(i).contains(xCoOrdinate, yCoOrdinate)) {
				b=true;
			}
		}
		return b;
	}
/**
 * <h1>Description</h1>
 * Method responds for a specified {@link #compositionTempIndicator_moved(MouseEvent)} response.
 * Method first evaluates if the given shape is either {@link Circle} or {@link Rectangle}
 * and changes the behavior of each accordingly.
 * @param shape {@link Shape}
 */
	private void geometrySelectionEffect(Shape shape,Color color,String type){
		if (type.equalsIgnoreCase(MOVED)) {
			if (shape.getClass()==new Circle().getClass()) {
				Circle circle = (Circle) shape;
				circle.setStroke(color);
				circle.setStrokeWidth(5);
				circle.setRadius(6);
			}
			else if (shape.getClass()==new Rectangle().getClass()) {
				Rectangle rectangle = (Rectangle) shape;
				rectangle.setStroke(color);
				rectangle.setStrokeWidth(5);
			}
		}
		else if (type.equalsIgnoreCase(CLICKED)) {
			
			double offset = 6;
			double length = 7;
			
			Line lineA;
			Line lineB; 
			Line lineC;  
			Line lineD;
			
			if (shape.getClass()==new Circle().getClass()) {
				Circle circle = (Circle) shape;
				double circleX = circle.getCenterX();
				double circleY = circle.getCenterY();
				double radius = circle.getRadius();
				
				lineA = new Line(circleX-radius-offset-length, circleY, circleX-radius-offset, circleY);
				lineA.setStrokeWidth(2);
				lineA.setId("crossLine");
				
				lineB = new Line(circleX,circleY-radius-offset-length,circleX,circleY-radius-offset);
				lineB.setStrokeWidth(2);
				lineB.setId("crossLine");
				
				lineC = new Line(circleX+radius+offset,circleY, circleX+radius+offset+length, circleY);
				lineC.setStrokeWidth(2);
				lineC.setId("crossLine");
				
				lineD = new Line(circleX,circleY+radius+offset,circleX,circleY+radius+offset+length);
				lineD.setStrokeWidth(2);
				lineD.setId("crossLine");
			
				targetRemoverById(new String[]{"crossLine"}, theDrawingPane, false);
				theDrawingPane.getChildren().addAll(lineA,lineB,lineC,lineD);
					
				circle.setStrokeWidth(4);
				circle.setRadius(3);
			}
			else if (shape.getClass()==new Rectangle().getClass()) {
				Rectangle rectangle = (Rectangle) shape;
				double widthHalf = rectangle.getWidth()/2;
				double heightHalf = rectangle.getHeight()/2;
				double recCenterX = rectangle.getX() + (widthHalf);
				double recCenterY = rectangle.getY() + (heightHalf);
				
				lineA = new Line(recCenterX-widthHalf-offset-length, recCenterY, recCenterX-widthHalf-offset, recCenterY);
				lineA.setStrokeWidth(2);
				lineA.setId("crossLine");
				
				lineB = new Line(recCenterX,recCenterY-heightHalf-offset-length,recCenterX,recCenterY-heightHalf-offset);
				lineB.setStrokeWidth(2);
				lineB.setId("crossLine");
				
				lineC = new Line(recCenterX+widthHalf+offset,recCenterY, recCenterX+widthHalf+offset+length, recCenterY);
				lineC.setStrokeWidth(2);
				lineC.setId("crossLine");
				
				lineD = new Line(recCenterX,recCenterY+heightHalf+offset,recCenterX,recCenterY+heightHalf+offset+length);
				lineD.setStrokeWidth(2);
				lineD.setId("crossLine");
				
				targetRemoverById(new String[]{"crossLine"}, theDrawingPane, false);
				theDrawingPane.getChildren().addAll(lineA,lineB,lineC,lineD);
				
				rectangle.setStrokeWidth(4);
			}
		}
	}
/**
 * <h1>Description</h1>
 * Method sets a given list containing {@link Circle} or {@link Rectangle} to a specified normal
 * state conditions.
 * @param shapeList {@link ArrayList}
 */
	private static void toNormalState(ArrayList<Shape> shapeList,ArrayList<Shape> exceptionShapes){
		if (exceptionShapes != null) {
			for (int i = 0; i < exceptionShapes.size(); i++) {
				shapeList.remove(exceptionShapes.get(i));
			}
		}
		for (int i = 0; i < shapeList.size(); i++) {
			if (shapeList.get(i).getClass()==new Circle().getClass()) {
				Circle circle = (Circle) shapeList.get(i);
				circle.setStroke(Color.BLACK);
				circle.setStrokeWidth(2);
				circle.setRadius(4);
			}
			else if (shapeList.get(i).getClass()==new Rectangle().getClass()) {
				Rectangle rectangle = (Rectangle) shapeList.get(i);
				rectangle.setStroke(Color.BLACK);
				rectangle.setFill(Color.RED);
				rectangle.setStrokeWidth(2);
			}
		}
	}
/**
 * <h1>Description</h1>
 * Sets the default state of popups <code>setDisabled</code> status,adds the items to the {@link #popupWindow}.
 * @return {@link Void}
 * @see #popupWindow
 */
	private void items_initializedStatus() {
		// Popup Items
		showNativeMarkers.setDisable(true);
		zoomOut.setDisable(true);
		removeMarker.setDisable(true);
		popupWindow.getItems().addAll(newDiagram,zoomIn,zoomOut,addMarker,removeMarker,pan,select,removeAllCustom,removeAllNative,showNativeMarkers,removeAll,closeSpace);
		// Menu Items View
		clearAllCustom_MI.setDisable(true);
		showAllNative_MI.setDisable(true);
		zoomOut_MI.setDisable(true);
		pan_MI.setDisable(true);
		select_MI.setDisable(true);
		save_MI.setDisable(false);
		saveAs_MI.setDisable(true);
		//Menu Items Edit
		clearAllCustom_MI.setDisable(true);
		showAllNative_MI.setDisable(true);
		
	}
/**
 * <h1>Description</h1>
 * Creates  a time line that starts from 0 and progresses forward every given time <i>time</i> {@link Integer
 * used for calculation in #mouse_Linger(MouseEvent)
 * @param milliSeconds {@link Integer} a specified time to count forward in milliseconds
 * @return {@link Void}
 */
	private void timeLine_Counter(int milliSeconds) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						Thread.sleep(milliSeconds);
						timeLine++;
					}
				} catch (InterruptedException e) {}
			}
		});
		t.start();
	}
/**
 * <h1>Description</h1>
 * This method initializes the native points navigation panel and is updated at runtime for the custom points that will be added.
 * @return {@link Void}
 */
	TreeItem<TableValues> tableRoot = new TreeItem<>();
	@SuppressWarnings("unchecked")
	private void treeTableConstructor_nativePoints() {
		
		TableValues value1 = new TableValues("Point 1:1538","None", "50", "1538","N/A","0.0","0.0","1", "Note about Point1");
		TableValues value2 = new TableValues("Point 2:1493","None","50", "1493","N/A","0","0","1", "Note about Point2");
		TableValues value3 = new TableValues("Point 3:1394","None", "50", "1394","N/A","0","0","1", "Note about Point3");
		TableValues value4 = new TableValues("Point 4:912","None","50", "912","N/A","0","0","1", "Note about Point4");
		//Temperature values,Names,and notes are place holders replace with correct values 
		TableValues value5 = new TableValues("Point 5:002","None", "0.02", "1000","N/A","0","0","1", "Note about Point5");
		TableValues value6 = new TableValues("Point 6:076","None", "0.76", "1000","N/A","0","0","1", "Note about Point6");
		TableValues value7 = new TableValues("Point 7:214","None", "2.14", "1000","N/A","0","0","1", "Note about Point7");
		TableValues value8 = new TableValues("Point 8:430","None","4.30", "1000","N/A","0","0","1", "Note about Point8");
		
		tableView.setEditable(true);
		points_column.setEditable(true);
		note_column.setEditable(true);
		coolingTime_column.setEditable(true);
		
		tableCustomPoints.setExpanded(true);
		
		
		points_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("points_value"));
		onPhaseName_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("onPhaseName_value"));
		cemmComp_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("composition_value"));
		temp_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("temp_value"));
		isOnPurePhase_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("onPurePhase_value"));
		rightFraction_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("rightFraction_value"));
		leftFraction_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("leftFraction_value"));
		coolingTime_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("coolingTime_value"));
		note_column.setCellValueFactory(new TreeItemPropertyValueFactory<>("note_value"));
		
		points_column.setCellFactory(TextFieldTreeTableCell.<TableValues>forTreeTableColumn());
		note_column.setCellFactory(TextFieldTreeTableCell.<TableValues>forTreeTableColumn());
		temp_column.setCellFactory(TextFieldTreeTableCell.<TableValues>forTreeTableColumn());
		cemmComp_column.setCellFactory(TextFieldTreeTableCell.<TableValues>forTreeTableColumn());
		onPhaseName_column.setCellFactory(TextFieldTreeTableCell.<TableValues>forTreeTableColumn());
		isOnPurePhase_column.setCellFactory(TextFieldTreeTableCell.<TableValues>forTreeTableColumn());
		rightFraction_column.setCellFactory(TextFieldTreeTableCell.<TableValues>forTreeTableColumn());
		leftFraction_column.setCellFactory(TextFieldTreeTableCell.<TableValues>forTreeTableColumn());
		coolingTime_column.setCellFactory(TextFieldTreeTableCell.<TableValues>forTreeTableColumn());
		
		TreeItem<TableValues> nativePoints = new TreeItem<>(nativeValue);
		 
		TreeItem<TableValues> item1 = new TreeItem<TableValues>(value1);
		TreeItem<TableValues> item2 = new TreeItem<TableValues>(value2);
		TreeItem<TableValues> item3 = new TreeItem<TableValues>(value3);
		TreeItem<TableValues> item4 = new TreeItem<TableValues>(value4);
		TreeItem<TableValues> item5 = new TreeItem<TableValues>(value5);
		TreeItem<TableValues> item6 = new TreeItem<TableValues>(value6);
		TreeItem<TableValues> item7 = new TreeItem<TableValues>(value7);
		TreeItem<TableValues> item8 = new TreeItem<TableValues>(value8);
		
		nativePoints.getChildren().addAll(item1,item2,item3,item4,item5,item6,item7,item8);
		nativePoints.setExpanded(true);
		
		tableRoot.getChildren().addAll(nativePoints,tableCustomPoints);
		tableView.setRoot(tableRoot);
		
		points_column.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<TableValues,String>>() {
			@Override
			public void handle(CellEditEvent<TableValues, String> event) {
				TreeItem<TableValues> values = event.getRowValue();
				TableValues val = values.getValue();
				val.setPoints_value(event.getNewValue().toString());
			}
		});
		note_column.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<TableValues,String>>() {
			@Override
			public void handle(CellEditEvent<TableValues, String> event) {
				TreeItem<TableValues> editedTreeItem = event.getRowValue();
				TableValues editedValue = editedTreeItem.getValue();
				editedValue.setNote_value(event.getNewValue().toString());
			}
		});
		coolingTime_column.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<TableValues,String>>() {
			@Override
			public void handle(CellEditEvent<TableValues, String> event) {
				TreeItem<TableValues> editedTreeItem = event.getRowValue();
				TableValues editedValue = editedTreeItem.getValue();
				editedValue.setCoolingTime_value(Double.parseDouble(event.getNewValue().toString()));
			}
		});
				
		treeTableSelectionResponder();
	}
/**
 * <h1>Description</h1>
 * Method responds to the selection of any elements on the TreeTable.
 * It Marks the corresponding points on the Working space.
 * @see #geometrySelectionEffect(Shape, Color, String)
 */
	public void treeTableSelectionResponder() {
		
		tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				toNormalState(geometryList, null);
			
				if (tableView.getSelectionModel().getSelectedIndex() == 9 || tableView.getSelectionModel().getSelectedIndex() == 0) {
					targetRemoverById(new String[]{"crossLine"}, theDrawingPane, false);
				}
				else {
					int indexAnomaly = 0;
					if (tableView.getSelectionModel().getSelectedIndex() <= 8) {
						indexAnomaly = 1;
					}
					else if (tableView.getSelectionModel().getSelectedIndex() > 8) {
						indexAnomaly = 2;
					}
					Shape s;
					try {
						s = geometryList.get(tableView.getSelectionModel().getSelectedIndex()-indexAnomaly);
						geometrySelectionEffect(s, Color.GREEN,CLICKED);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
	}
	int selectedIndex = 0; 
	public void removeElementFromTable(){
		removeElementTable.setDisable(false);
		tableCustomPoints.getChildren().remove(selectedIndex-10);
		Shape s = geometryList.get(selectedIndex-2);
		theDrawingPane.getChildren().remove(s);
		geometryList.remove(s);
		targetRemoverById(new String[]{"crossLine"}, theDrawingPane, false);
	}
	public void removeAllCutsomFromTable(){
		tableCustomPoints.getChildren().clear();
		targetRemoverById(new String[]{"crossLine"}, theDrawingPane, false);
		targetRemoverById(new String[]{"customRectangles"}, geometryList);
	}
	public void targetRemoverById(String[] targetElementID,ArrayList<Shape> theList){
		for (int j = 0; j < targetElementID.length; j++) {
			for (int i = 0; i < theList.size(); i++) {
				if (theList.get(i).getId().toString() == targetElementID[j]) {
					theList.remove(i);
				}
			}
		}
	}
	public void treeTableEventHandler(MouseEvent e){
		if (e.isPopupTrigger()) {
			selectedIndex = tableView.getSelectionModel().getSelectedIndex();
			treeTableHolder.getChildren().remove(popMenuTable);
			popMenuTable.show(treeTableHolder, e.getSceneX(), e.getSceneY());
			
			removeAllCustomTable.setDisable(geometryList.size()>9 ? false : true);
			removeElementTable.setDisable(selectedIndex < 10 ? true : false);

		}
	}
	public void treeTablePopUpinitialize(){
		popMenuTable.getItems().addAll(removeElementTable,removeAllCustomTable);
		removeElementTable.setDisable(true);
		removeElementTable.setOnAction(lm -> removeElementFromTable());
		removeAllCustomTable.setOnAction(lm -> removeAllCutsomFromTable());
	}
	
/**
 * <h1>Description</h1>
 * Marks native points and adds on the {@link #theDrawingPane}.
 * @return {@link Void}
 * @see #theDrawingPane
 */
	private void markNative_UniquePoints(){
		theDrawingPane.getChildren().addAll(native1538,native1493,native1394,native912,native430,native214,native076,native002);
	}
	
	/**
	 * <h1>Description<h1>
	 * Method responsible for adjusting the proper view port on a zoomed image and generates the right
	 * Mouse Event X and Y positon that will suit the adjusted ViewPort.
	 * @param cemmentite_Comp
	 * @param temp_Celcius
	 * @return Void
	 */
	private void viewAdjuster_valueSetter(double cemmentite_Comp,double temp_Celcius,boolean calculateFrac){
		
		int xOn1X = phaseMath.comp_To_mouseX(false, cemmentite_Comp, viewPortX);
		int yOn1X = phaseMath.temp_To_mouseY(false, temp_Celcius, viewPortY);
		
		ViewAdjuster adjust = new ViewAdjuster(xOn1X, yOn1X, viewPortX, viewPortY, isZoom);
		if (adjust.viewPortChanged) {
			viewPortX = (int) adjust.getViewPortX();
			viewPortY = (int) adjust.getViewPortY();
		}
		
		layerAImageView.setViewport(new Rectangle2D(viewPortX, viewPortY, 1000, 600));
		layerBImageView.setViewport(new Rectangle2D(viewPortX, viewPortY, 1000, 600));
		
		int adj_MouseX = phaseMath.comp_To_mouseX(isZoom, cemmentite_Comp, viewPortX);
		int adj_MouseY = phaseMath.temp_To_mouseY(isZoom, temp_Celcius, viewPortY);
		

		pointPlacer();
		if (calculateFrac) {
			addMarker(adj_MouseX, adj_MouseY,true);
		}
		
	}

/**
 * <h1>Description</h1>
 * Draws Point locator to the {@link #theDrawingPane} with pre-designated values.
 * @param e {@link ActionEvent}
 * @return {@link Void}
 */
	public void uniquePoints_Combo_Selected(ActionEvent e){
		
		String test = uniquePoints.getValue();
		switch (test) {
		case "1538C Point":viewAdjuster_valueSetter(0.55,1538,true);
		break;
		case "1493C Point":viewAdjuster_valueSetter(3.80,1493,true);
		break;
		case "1394C Point":viewAdjuster_valueSetter(0.30,1394,true);
		break;
		case "912C Point" :viewAdjuster_valueSetter(0.45,912,true);
		break;
		case "0.02% Point":viewAdjuster_valueSetter(1.5,727,true);
		break;
		case "0.76% Point":viewAdjuster_valueSetter(11.34,727,true);
		break;
		case "2.14% Point":viewAdjuster_valueSetter(31.94,1147,true);
		break;
		case "4.30% Point":viewAdjuster_valueSetter(64.18,1147,true);
		break;
		default:
			break;
		}
	}
/**
 * <h1>Description</h1>
 * This Method take cares the disabling and enabling of components based on the toggle condition of the respective radioButton Peers.
 * @param e {@link ActionEvent}
 * @return null
 */
	public void disableComponents(ActionEvent e){
		if (e.getSource()==comp_mw_rb) {
			for (int i = 0; i < mw_Components.size(); i++) {
				if (mw_Components.get(i) instanceof RadioButton){
					mw_Components.get(i).setDisable(false);
				}
			}
			
			if (carbon_mw_rb.isSelected()) {
					disable_target_components(new TextField[]{carbon_mw_tf, cemmentite_mw_tf, iron_mw_tf});
			}
			else if (cemmentite_mw_rb.isSelected()) {
					disable_target_components(new TextField[]{cemmentite_mw_tf,carbon_mw_tf,iron_mw_tf});
			}
			else if (iron_mw_rb.isSelected()) {
					disable_target_components(new TextField[]{iron_mw_tf,carbon_mw_tf, cemmentite_mw_tf});
			}
			
		for (int i = 0; i < aw_Components.size(); i++) {
			aw_Components.get(i).setDisable(true);
			if (aw_Components.get(i) instanceof TextField) {
				TextField field = (TextField)aw_Components.get(i);
				field.clear();
			}
		}
		}
		else if (e.getSource()==comp_aw_rb) {
			for (int i = 0; i < aw_Components.size(); i++) {
				if (aw_Components.get(i) instanceof RadioButton){
					aw_Components.get(i).setDisable(false);
				}
			}
			if (carbon_aw_rb.isSelected()) {
				disable_target_components(new TextField[]{carbon_aw_tf, cemmentite_aw_tf, iron_aw_tf});
			}
			else if (cemmentite_aw_rb.isSelected()) {
				disable_target_components(new TextField[]{cemmentite_aw_tf,carbon_aw_tf,iron_aw_tf});
			}
			else if (iron_aw_rb.isSelected()) {
				disable_target_components(new TextField[]{iron_aw_tf,carbon_aw_tf, cemmentite_aw_tf});
			}
			for (int i = 0; i < mw_Components.size(); i++) {
				mw_Components.get(i).setDisable(true);
				if (mw_Components.get(i) instanceof TextField) {
					TextField field = (TextField)mw_Components.get(i);
					field.clear();
				}
			}
		}
		else if (e.getSource()==centigrade_rb) {
			centigrade_tf.setDisable(false);
			farhanite_tf.setDisable(true);
			farhanite_tf.clear();
		}
		else if (e.getSource()==farhanite_rb) {
			farhanite_tf.setDisable(false);
			centigrade_tf.setDisable(true);
			centigrade_tf.clear();
		}
		// for mass weight secondary components
		else if (e.getSource()==carbon_mw_rb) {
			disable_target_components(new TextField[]{carbon_mw_tf, cemmentite_mw_tf, iron_mw_tf});
		}
		else if (e.getSource()==cemmentite_mw_rb) {
			disable_target_components(new TextField[]{cemmentite_mw_tf,carbon_mw_tf, iron_mw_tf});
		}
		else if (e.getSource()==iron_mw_rb) {
			disable_target_components(new TextField[]{iron_mw_tf,carbon_mw_tf, cemmentite_mw_tf});
		}
		//for atomic weight secondary components
		else if (e.getSource()==carbon_aw_rb) {
			disable_target_components(new TextField[]{carbon_aw_tf,cemmentite_aw_tf,iron_aw_tf});
		}
		else if (e.getSource()==cemmentite_aw_rb) {
			disable_target_components(new TextField[]{cemmentite_aw_tf,carbon_aw_tf,iron_aw_tf});
		}
		else if (e.getSource()==iron_aw_rb) {
			disable_target_components(new TextField[]{iron_aw_tf,carbon_aw_tf,cemmentite_aw_tf});
		}
		
	}
/**
 * <h1>Description</h1>
 * What ever input the user types in this set of conditional method,first it converts that value to Cemmentitie Mass weight and Centigrade
 * then Proceeds to calculate the phase Fraction using {@link #completeFrac(int, int, SimpleStringProperty, boolean)} method.
 * @param e {@link ActionEvent}
 * @return {@link NullType}
 */
	public void onButtonAction(ActionEvent e){
		double cemmentite_Holder = 0;
		double centigrade_Holder = 0;
		
		class parser {
			/**
			 * <h1>Description</h1>
			 * Handles the first entry when the user uses the manual adjuster, and handles some exception as to weather it's a proper number format 
			 * Takes extra variable of the minValue in respect of his twin,that is it will set it  to that minimum value if number format exception
			 * occurs, by default that value of minValue is 0.
			 * @param  minValue {@link Integer}
			 * @return {@link Double}
			 */
			public double handleError(String string,int minValue){
				Double value;
				try {
					 value = Double.parseDouble(string);
				} catch (NumberFormatException e) {
					errorMessage_lb.setText("Number Format Exception : Not numeric value");
					value = (double)minValue;
				}
				return value;
			}
			/**
			 * <h1>Description</h1>
			 * Handles the first entry when the user uses the manual adjuster, and handles some exception as to weather it's a proper number format 
			 * Takes extra variable of the minValue in respect of his twin,that is it will set it  to that minimum value if number format exception
			 * occurs, by default that value of minValue is 0.	
			 * @param string {@link String}
			 * @return {@link Double}
			 */	
			public double handleError(String string){
				Double value;
				try {
					value = Double.parseDouble(string);
					} catch (NumberFormatException e) {
					errorMessage_lb.setText("Number Format Exception : Not numeric value");
					value = (double)0;
					}
				return value;
			}
		}
		if (e.getSource()==calcFrac_but) {
			
			if (comp_mw_rb.isSelected()) {
				if (carbon_mw_rb.isSelected()) {
					cemmentite_Holder = phaseMath.carbon_mw_in(new parser().handleError(carbon_mw_tf.getText())); 
				}
				if (cemmentite_mw_rb.isSelected()) {
					cemmentite_Holder = phaseMath.cemmentite_mw_in(new parser().handleError(cemmentite_mw_tf.getText()));
				}
				if (iron_mw_rb.isSelected()) {
					cemmentite_Holder = phaseMath.iron_mw_in(new parser().handleError(iron_mw_tf.getText()));
				}
			}
			else if (comp_aw_rb.isSelected()) {
				if (carbon_aw_rb.isSelected()) {
					cemmentite_Holder = phaseMath.carbon_aw_in(new parser().handleError(carbon_aw_tf.getText()));
				}
				if (cemmentite_aw_rb.isSelected()) {
					cemmentite_Holder = phaseMath.cemmentite_aw_in(new parser().handleError(cemmentite_aw_tf.getText()));
				}
				if (iron_aw_rb.isSelected()) {
					cemmentite_Holder = phaseMath.iron_aw_in(new parser().handleError(iron_aw_tf.getText()));
				}
			}
			
			if (centigrade_rb.isSelected()) {
				centigrade_Holder =phaseMath.celcius_in(new parser().handleError(centigrade_tf.getText(),400));
			}
			if (farhanite_rb.isSelected()) {
				centigrade_Holder = phaseMath.farhanite_in(new parser().handleError(farhanite_tf.getText(),752));
			}
			viewAdjuster_valueSetter(cemmentite_Holder, centigrade_Holder, true);
		}
		
		if (e.getSource()==export_but) {
			generateExportTable();

		}
	}
public void generateExportTable() {
	TreeTableView<TableValues> exportedTreeTable = new TreeTableView<>();
	exportedTreeTable.getColumns().addAll(tableView.getColumns());
	
	TreeTableColumn<TableValues, CheckBox> checkPoints = new TreeTableColumn<>();
	checkPoints.setText("Mark");
	checkPoints.setPrefWidth(70);
	checkPoints.setCellValueFactory(new TreeItemPropertyValueFactory<>("check_value"));
	exportedTreeTable.getColumns().add(0,checkPoints);
	
	exportedTreeTable.setEditable(true);
	exportedTreeTable.getColumns().get(1).setEditable(true);
	exportedTreeTable.getColumns().get(7).setEditable(true);
	exportedTreeTable.getColumns().get(9).setEditable(true);
	
	exportedTreeTable.setRoot(tableRoot);
	
	CheckBox customCheckBox = (CheckBox)exportedTreeTable.getColumns().get(0).getCellData(1);
	customCheckBox.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			if (customCheckBox.isSelected()) {
				for (int i = 2; i < 10; i++) {
					CheckBox ch2 = (CheckBox)exportedTreeTable.getColumns().get(0).getCellData(i);
					ch2.setSelected(true);
				}
			}
			else if (!(customCheckBox.isSelected())){
				for (int i = 2; i < 10; i++) {
					CheckBox ch2 = (CheckBox)exportedTreeTable.getColumns().get(0).getCellData(i);
					ch2.setSelected(false);
				}
			}
			
		}
	});
	CheckBox nativeCheckBox = (CheckBox)exportedTreeTable.getColumns().get(0).getCellData(10);
	
	int nativePointsSize = exportedTreeTable.getRoot().getChildren().get(0).getChildren().size();
	int customPointsSize = exportedTreeTable.getRoot().getChildren().get(1).getChildren().size();
	
	nativeCheckBox.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			if (nativeCheckBox.isSelected()) {
				for (int i = 10; i < nativePointsSize+11; i++) {
					CheckBox ch2 = (CheckBox)exportedTreeTable.getColumns().get(0).getCellData(i);
					ch2.setSelected(true);
				}
			}
			else if (!(nativeCheckBox.isSelected())){
				for (int i = 10; i < nativePointsSize+11; i++) {
					CheckBox ch2 = (CheckBox)exportedTreeTable.getColumns().get(0).getCellData(i);
					ch2.setSelected(false);
				}
			}
		}
	});
	
	TreeTableColumn<TableValues, TempratureListHolder> calc_tempValues = new TreeTableColumn<>();
	calc_tempValues.setText("Temp Value Points");
	calc_tempValues.setPrefWidth(160);
	calc_tempValues.setCellValueFactory(new TreeItemPropertyValueFactory<>("listHolder_value"));
	exportedTreeTable.getColumns().add(8,calc_tempValues);
	
	for (int i = 1; i < nativePointsSize+customPointsSize+3; i++) {
		TempratureListHolder tlHolder = (TempratureListHolder) exportedTreeTable.getColumns().get(8).getCellData(i);
		try {
			double tempVal = Double.parseDouble((String) exportedTreeTable.getColumns().get(4).getCellData(i));
			System.out.println(tempVal);
			tlHolder.setMaxTempValue(tempVal);
		} catch (NumberFormatException e1) {
			System.err.println("Number format exception");
			tlHolder.removeElements();
		}
	}
	ExportedDataHandler dataHandler = new ExportedDataHandler(exportedTreeTable);
	dataHandler.showTable();
}

/**
 * <h1>Description</h1>
 * Methods to be invoked when the user clicks on the phaseGraph image.
 * It first evaluates the <code>isPopupTrigger</code> method.(for either value the click will respond).
 * @param e {@link MouseEvent}
 * @return {@link Void}
 * @see #completeFrac
 * @see #popUpActions(MouseEvent)
 */
	public void compositionTempIndicator_clicked(MouseEvent e) {
		if (!(e.isPopupTrigger())) {
			onGeometryClickResponder(e);
			completeFrac((int)e.getX(),(int)e.getY(),phaseName,false);
			popupWindow.hide();
			
		}	
		
		else if (e.isPopupTrigger()) {
			popUpAction(e);
		}
	}
/**
 * @param e
 */
public void popUpAction(MouseEvent e) {
	if (isCoordinateContainedInGeometry((int)e.getX(),(int)e.getY(), geometryList)) {
		removeMarker.setDisable(false);
	}
	else if (!(isCoordinateContainedInGeometry((int)e.getX(),(int)e.getY(), geometryList))) {
		removeMarker.setDisable(true);
	}
	popupWindow.show(theDrawingPane, e.getX(), e.getY());
	popUpActions(e);
}
/**
 * @param e
 */
public void onGeometryClickResponder(MouseEvent e) {
	Shape s = containsGeometry(e, geometryList,Color.BLACK);
	int indexAnomaly = 0;
	for (int i = 0; i < geometryList.size(); i++) {
		if (geometryList.get(i) == s) {
			if (i <= 7) {
				indexAnomaly = 1;
			}
			else if (i>7) {
				indexAnomaly = 2;
			}
			tableView.getSelectionModel().select(i+indexAnomaly);
			geometrySelectionEffect(s, Color.BLACK, CLICKED);
		}
	}
}
	
/**
 * <h1>Description</h1>
 * Method Handling the Actions performed by the popUp Mouse Event.
 * @param e {@link MouseEvent}
 * @return {@link Void}
 * @see #compositionTempIndicator_clicked(MouseEvent)
 * @see #addMarker
 * @see #removeAll
 * @see #closeSpace
 * @see #newDiagram
 * @see #zoomIn
 * @see #zoomOut
 */
	public void popUpActions(MouseEvent e) {
		newDiagram.setOnAction (lam -> openFile()); //TODO New Diagram function
		zoomIn.setOnAction(lam -> onZoomIn());
		zoomOut.setOnAction(lam -> onZoomOut());
		addMarker.setOnAction(lam -> addMarker(e.getX(),e.getY(),false));
		removeMarker.setOnAction(lam -> removeMarker(e));
		removeAllCustom.setOnAction(lam -> removeAllCustom(theDrawingPane));
		removeAllNative.setOnAction(lam -> removeAllNative(theDrawingPane));
		showNativeMarkers.setOnAction(lam -> showNativeMarkers());
		pan.setOnAction(lam -> panAction());
		select.setOnAction(lam -> selectAction());
		removeAll.setOnAction(lam -> removeAllMarkers(theDrawingPane));
		closeSpace.setOnAction(lam -> closeSpace());
	}
	public void panAction(){
		//Changing Status of popup Menu items
		pan.setDisable(true);
		select.setDisable(false);
		//Changing Status of Menu Item Edit
		pan_MI.setDisable(true);
		select_MI.setDisable(false);
		
		targetRemoverById(new String[]{"crossLine"}, theDrawingPane, false);
		
		pxReader = layerAImageView.getImage().getPixelReader();
		
		layerAImageView.setViewport(new Rectangle2D(viewPortX,viewPortY, 1000, 600));
		layerBImageView.setViewport(new Rectangle2D(viewPortX,viewPortY, 1000, 600));
		theDrawingPane.setCursor(Cursor.OPEN_HAND);
		targetRemoverById(new String[]{"nativeCircles","customRectangles","crossLine"},theDrawingPane,true);
		
		theDrawingPane.setOnMouseMoved(new EventHandler<Event>() {
			@Override 
			public void handle(Event event) {pxReader = layerAImageView.getImage().getPixelReader();}}); //Cancels any Previous Methods
		
		theDrawingPane.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				theDrawingPane.setCursor(Cursor.CLOSED_HAND);
				MouseEvent e = (MouseEvent) event;
				initX = (int) e.getX();
				initY = (int) e.getY();
				
			}
		});
		theDrawingPane.setOnMouseReleased(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				theDrawingPane.setCursor(Cursor.OPEN_HAND);
				
			}
		});
		theDrawingPane.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				MouseEvent e = (MouseEvent)event;
				if (e.isPopupTrigger()) {
					popUpAction(e);
					theDrawingPane.setCursor(Cursor.OPEN_HAND);
				}
			}
		});
		theDrawingPane.setOnMouseDragged(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				
				theDrawingPane.setCursor(Cursor.CLOSED_HAND);
				targetRemoverById(new String[]{"crossLine"}, theDrawingPane, false);
				targetRemoverById(new String[]{"nativeCircles","customRectangles"},theDrawingPane,true);
				MouseEvent e = (MouseEvent) event;
				
				int finalX = (int)e.getX();
				int finalY = (int)e.getY();
				
				changeX = finalX-initX;
				changeY = finalY - initY;
				initX = finalX;
				initY = finalY;
				viewPortX = viewPortX+changeX;
				viewPortY = viewPortY+changeY;
				
				viewPortX = (int) clamp(viewPortX, 0, layerAImageView.getImage().getWidth() - 1000);
				viewPortY = (int) clamp(viewPortY, 0, layerAImageView.getImage().getHeight() - 600);
			
				layerAImageView.setViewport(new Rectangle2D(viewPortX,viewPortY, 1000, 600)); 
				layerBImageView.setViewport(new Rectangle2D(viewPortX,viewPortY, 1000, 600));
				pointPlacer();
			}
			 
		});
	}
	public void selectAction(){
		//Changing Disable status of popup Menu items
		select.setDisable(true);
		pan.setDisable(isZoom ? false : true);
		//Changing Disable status of Menu item on View
		select_MI.setDisable(true);
		pan_MI.setDisable(isZoom ? false : true);
		
		pxReader = layerAImageView.getImage().getPixelReader();
		layerAImageView.setViewport(new Rectangle2D(viewPortX,viewPortY, 1000, 600));
		theDrawingPane.setCursor(Cursor.CROSSHAIR);
		
		theDrawingPane.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {}});//Cancels Previous assigned Methods on Pressed Events
		theDrawingPane.setOnMouseReleased(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {}});//Cancels Previous assigned Methods on Released Events
		theDrawingPane.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				compositionTempIndicator_clicked((MouseEvent)event);
			}
		});
		theDrawingPane.setOnMouseDragged(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				compositionTempIndicator_moved((MouseEvent)event);
			}
		});
		theDrawingPane.setOnMouseMoved(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				compositionTempIndicator_moved((MouseEvent)event);
			}
		});
		
	}
	public static Stage primaryStage = new Stage();
	public void closeSpace(){
		ExitConfirmation_Controler exitConfirmation = new ExitConfirmation_Controler();
		exitConfirmation.show();
	}
	public void removeMarker(MouseEvent e){
		Shape s = containsGeometry(e, geometryList, null);
		int index = geometryList.indexOf(s);
		theDrawingPane.getChildren().remove(s);
		tableCustomPoints.getChildren().remove(index-8);
		geometryList.remove(index);
		targetRemoverById(new String[]{"crossLine"}, theDrawingPane, false);
	}
	
	int squareQuantity = 0;
	int nameTag = 1;
	int customIndex = 10;
/**
 * <h1>Description</h1>
 * Method invoked when {@link #addMarker(MouseEvent)} is selected in the popup menu.
 * Creates a custom point, a rectangle to mark the spot.
 * @param e {@link MouseEvent}
 * @return {@link Void}
 * @see #customPoints
 * @see #addMarker(MouseEvent)
 */	
	public void addMarker(double mouseX,double mouseY,boolean manuallyLocated){
		completeFrac((int)mouseX,(int)mouseY,phaseName,false);
		
		removeAll.setDisable(false);
		removeElementTable.setDisable(false);
		
		Rectangle r = new Rectangle(mouseX, mouseY, 6, 6);
		r.setId("customRectangles");
		r.setFill(Color.RED);r.setStroke(Color.BLACK);r.setStrokeWidth(2);
		theDrawingPane.getChildren().add(r);
		geometryList.add(r);
		customRectangles.add(r);
		
		initialCoordinateCustom.add(isZoom ? new Double[] {(mouseX+viewPortX)/2,(mouseY+viewPortY)/2} : new Double[] {mouseX,mouseY});
		
		String recName = "Marker " + nameTag;
		customPoints.add(recName);
		squareQuantity++;
		nameTag++;
		
		String pointName = "Point : "+customIndex;
		String onPhaseName = phaseName.get();
		String pointCemmentiteValue = Double.toString(PhaseMath.mouseX_To_comp(isZoom,(int)mouseX,viewPortX));
		String pointTempValue = Double.toString(PhaseMath.mouseY_To_temp(isZoom,(int)mouseY,viewPortY));
		String isOnPurePhase = isPurePhase;
		String rightFractionValue = Double.toString(rightFrac);
		String leftFractionValue = Double.toString(leftFrac);
		String coolingTime = "1"; //Holder value, Can be edited on the table (Cooling Time = Drop in Centigrade per second
		String pointNote = manuallyLocated ? "Located Manually " : "Add Note Here";
		
		TableValues newValue = new TableValues(pointName,onPhaseName,pointCemmentiteValue, pointTempValue,isOnPurePhase,rightFractionValue,leftFractionValue,coolingTime,pointNote);
		TreeItem<TableValues> item = new TreeItem<TableValues>(newValue);
		tableCustomPoints.getChildren().add(item);
		customIndex ++;
		
	}
/**
 * <h1>Description</h1>
 * Method invoked when {@link #removeAllMarkers(AnchorPane)} is selected.
 * Removes all Custom points from the diagram and from the table.
 * @param theContainer AnchorPane
 * @return {@link Void}
 * @see #targetRemoverById(String[], AnchorPane, boolean)
 * @see #removeAllMarkers(AnchorPane)
 */
	public void removeAllMarkers(AnchorPane theContainer){
		targetRemoverById(new String[]{"customRectangles","nativeCircles"},theContainer,false);
		targetRemoverById(new String[]{"customRectangles"}, geometryList);
		removeAll.setDisable(true);
		showNativeMarkers.setDisable(false);
		clearAll_MI.setDisable(true);
		clearAllNative_MI.setDisable(true);
		clearAllCustom_MI.setDisable(true);
		showAllNative_MI.setDisable(false);
		
	}
/**
 * <h1>Description</h1>
 * Method invoked when {@link #removeAllCustom(AnchorPane)} is selected
 * Method removes all Custom points from <code>theContainer</code>.
 * @param theContainer
 * @return {@link Void}
 * @see #targetRemoverById(String[], AnchorPane, boolean)
 * @see #removeAllCustom(AnchorPane)
 */
	public void removeAllCustom(AnchorPane theContainer){
		targetRemoverById(new String[]{"customRectangles"},theContainer,false);
		targetRemoverById(new String[]{"customRectangles"}, geometryList);
		tableCustomPoints.getChildren().clear();
		removeAllCutsomFromTable();
		removeAllCustom.setDisable(true);
		clearAllCustom_MI.setDisable(true);
		
	}
/**
 * <h1>Description</h1>
 * Method removes all Native points from theContainer.
 * Responsible for seting the diabled property of {@link #showNativeMarkers to <code>false</code>
 * @param theContainer
 * @return {@link Void}
 * @see #showNativeMarkers()
 * @see #targetRemoverById(String[], AnchorPane, boolean)
 */
	public void removeAllNative(AnchorPane theContainer){
		targetRemoverById(new String[]{"nativeCircles"},theContainer,false);
		showNativeMarkers.setDisable(false);
		removeAllNative.setDisable(true);
		clearAllNative_MI.setDisable(true);
		showAllNative_MI.setDisable(false);
	}
/**
 * <h1>Description</h1>
 * Method reinstates the native Markers back to their place.
 * @return {@link Void}
 * @see #showNativeMarkers
 */
	private void showNativeMarkers(){
		markNative_UniquePoints();
		showNativeMarkers.setDisable(true);
		showAllNative_MI.setDisable(true);
		clearAllNative_MI.setDisable(false);
		
	}
/**
 * <h1>Description</h1>
 * Selects a specific Target to either remove or keep from the Container(Parent) provided in the parameter
 * <h2>The Boolean parameter decides</h2>
 * <p><i>elementStays == <code>true</code></i> - The elements in the array <code>targetElement</code> is to be kept and all removed.</p>
 * <p><i>elementStays == <code>false</code></i> - The elements in the array <code>targetElement</code> removed and all else kept.</p>
 * @param targetElementID {@link String}
 * @param elementStays {@link Boolean}
 * @param theContainer {@link AnchorPane}
 * @return {@link Void}
 */
	public void targetRemoverById(String[] targetElementID,AnchorPane theContainer,boolean elementStays){
		String s1;
		String s2;
		for (int i = 0; i < targetElementID.length; i++) {
			s1 = targetElementID[i];
			if (elementStays==false) {
				while (contains(new String[]{s1},theContainer)) {
					for (int j = 0; j < theContainer.getChildren().size(); j++) {
						s2 = theContainer.getChildren().get(j).getId();
						if (s2!=null && s2.trim().equalsIgnoreCase(s1.trim())) {
							theContainer.getChildren().remove(j);
						}
					}
				}
			}
			else if (elementStays==true) {
				while (ifOnlyContainsID(targetElementID,theContainer)) {
					for (int j = 0; j < theContainer.getChildren().size(); j++) {
						if(!(isElementInGroup(theContainer.getChildren().get(j).getId(), targetElementID))){
							theContainer.getChildren().remove(j);
						}
					}
				}
			}
		}
	}
/**
 * <h1>Description</h1>
 * Method Returns true if the <i>theConatiner</i> contains only elements with ID's in objectId array.
 * @param objectId {@link String}
 * @param theContainer {@link AnchorPane}
 * @return {@link Boolean}
 */
	private boolean ifOnlyContainsID(String[] objectId,AnchorPane theContainer){
		boolean b = false;
		for (int j = 0; j < objectId.length; j++) {
			for (int i = 0; i < theContainer.getChildren().size(); i++) {
				if (theContainer.getChildren().get(i).getId() == null) {
					b= true;
				}
			}
		}
		return b;
	}
	
/**
 * <h1>Description</h1>
 * Method returns true if the id in the array objectId is contained inside theContainer.
 * @param objectId {@link String}
 * @param theContainer {@link AnchorPane}
 * @return {@link Boolean}
 */
	private boolean contains(String[] objectId,AnchorPane theContainer){
		boolean b =false;
		for (int j = 0; j < objectId.length; j++) {
			for (int i = 0; i < theContainer.getChildren().size(); i++) {
				if (theContainer.getChildren().get(i).getId() != null && theContainer.getChildren().get(i).getId().equalsIgnoreCase(objectId[j])) {
				b= true;
				}
			}
		}
		return b;
	}
/**
 * <h1>Description</h1>
 * Method returns true if theClassName is an element of the String Array theGroup.
 * @param theClassName
 * @param theGroup
 * @return {@link Boolean}
 */
	private boolean isElementInGroup(String theClassName,String[] theGroup){
		boolean b = false;
		for (int i = 0; i < theGroup.length; i++) {
			if (theGroup[i].equals(theClassName)) {
				b=true;
			}
		}
		return b;
	}
/**
 * <h1>Description</h1>
 * <p>This Method takes x and y coordinate values,converts those values to {@link #cemmentite_mw_in(double)} and {@link #centigrade_rb} pairs and uses those
 * values to paint right and left fraction.The Method is also divided by conditionals as to weather it's a pure or mixed
 * phase.,and treats them accordingly.This method is also responsible for the painting cross that indicates position.</p>
 * @param x {@link Integer}
 * @param y {@link Integer}
 * @param stringValue {@link SimpleStringProperty}
 * @param isMoving {@link Boolean}
 * @return {@link NullType}
 * @see #isOnMixedPhase(int, int)
 * @see #isOnPurePhase(int, int)
 */
	public void completeFrac(int x,int y,SimpleStringProperty stringValue, boolean isMoving) {
		
		double percentileCementite = PhaseMath.mouseX_To_comp(isZoom,x,viewPortX);
		double percentileIron =  Math.abs(percentileCementite-100.00);
		double temprature = PhaseMath.mouseY_To_temp(isZoom,y,viewPortY);
		
		int pxlX = (int) phaseMath.pxlReaderX(x, viewPortX, isZoom);
		int pxlY = (int) phaseMath.pxlReaderY(y, viewPortY, isZoom);
		
		
		targetRemoverById(new String[]{"nativeCircles","customRectangles","crossLine"},theDrawingPane,true);
		
		/**
		 * @author Nathan
		 * <h1>Description<h1>
		 * Handles the Properties of Displayers when calcFrac is invoked.
		 */
		class DisplayHandler{
			
			public void strokeWidth(int stroke,Color color,Line[] lines,String id){
				for (int i = 0; i < lines.length; i++) {
					lines[i].setStrokeWidth(stroke);
					lines[i].setStroke(color);
					theDrawingPane.getChildren().add(lines[i]);
					
					lines[i].setStartX(lines[i].getStartX() == 0 ? 0 : lines[i].getStartX());
					lines[i].setEndX(lines[i].getEndX() == 1000 ? 1000 : lines[i].getEndX());
					
					lines[i].setStartY(lines[i].getStartY() == 0 ? 0 : lines[i].getStartY());
					lines[i].setEndY(lines[i].getEndY() == 600 ? 600 : lines[i].getEndY());
					lines[i].setId(id);
				}
			}
			public VBox composition_temprature_displayer(double composition,double temprature){
				Label cemmComp = new Label("Cemmentite Comp  : " + composition + "%" );
				Label tempDisp = new Label("Temprature Celcius : " + temprature + '\u00B0');
						
				VBox cont = new VBox();
				cont.setStyle("-fx-background-color: lightGray;");
				
				cont.setOpacity(0.6);
				cont.getChildren().addAll(cemmComp,tempDisp);
								
				cont.setLayoutX((x>500) ? (double)x-155 : (double)x+10);
				cont.setLayoutY((y>300) ? (double)y-45 : (double)y+10);
				
				return cont;
				
			}
		}
		if (!isZoom &((x>1000)|(y>600))) {
			errorMessage_lb.setText( "Value Exceed Bound Error : Value exceeds bound");
		}
		else {
				isPurePhase = isOnPurePhase(pxlX,pxlY) ? "Yes" : "No";
				
				ironComp = percentileIron; //Export for IronComp
				cemmentiteComp = percentileCementite; //Export for cemmentitieComp
				tempCent = temprature; //Export for tempCent
				tempFar = phaseMath.toFarhanite(temprature); //Export for tempFar
				
				fractionReturn(pxlX,pxlY);
				
				clickedLine1 = new Line(0,y,1000,y);
				clickedLine2 = new Line(x,0,x,600);
				//
				int lineOffSet = 20;
				int shadowOffSet = 6;
				DisplayHandler lineSetter = new DisplayHandler();
				lineSetter.strokeWidth(3, Color.DARKVIOLET, new Line[]{new Line(0,y,x-lineOffSet,y),new Line(x,0,x,y-lineOffSet),new Line(x+lineOffSet, y, 1000, y),new Line(x, y+lineOffSet, x, 600)},"compIndicators");
				lineSetter.strokeWidth(3, Color.DARKVIOLET, new Line[]{new Line(x-shadowOffSet,y-shadowOffSet,x+shadowOffSet,y+shadowOffSet),new Line(x-shadowOffSet, y+shadowOffSet, x+shadowOffSet, y-shadowOffSet)},"xMarker");
				//
				clickedLine1.setStrokeWidth(3);	
				clickedLine2.setStrokeWidth(3); 
				
				ArrayList<String> neighborNames = returnPhaseName(pxlX-viewPortX, pxlY-viewPortY, stringValue, isMoving); //Export for PhaseName
				rightFractionName.setText(neighborNames.get(0) + " frac.");
				leftFractionName.setText(neighborNames.get(1)+ " frac.");
				
				phaseName_click.setText(phaseName.get());
				purePhase_click.setText(isPurePhase);
				rightFraction_click.setText(Double.toString(rightFrac));
				leftFraction_click.setText(Double.toString(leftFrac));
				
				VBox valueDisplayer = lineSetter.composition_temprature_displayer(cemmentiteComp, tempCent);
				theDrawingPane.getChildren().addAll(clickedLine1,clickedLine2,valueDisplayer);  
									
			if (isPure == false) {
				final int ovalValue = 4;
				final int textOffset_y = 5;
				final int textOffset_x = 4;
					
				Circle rightMarker = new Circle(markerRX-viewPortX, mouseY-viewPortY,ovalValue);
				Circle leftMarker = new Circle(markerLX-viewPortX, mouseY-viewPortY,ovalValue);
					
				Text rightText = new Text(markerRX-textOffset_x-viewPortX,mouseY-textOffset_y-viewPortY,"Rf");
				Text leftText = new Text(markerLX-textOffset_x-viewPortX,mouseY-textOffset_y-viewPortY,"Lf");
					
				rightText.setStroke(Color.BLUE);
				leftText.setStroke(Color.RED);
					
				rightMarker.setFill(Color.BLUE);
				leftMarker.setFill(Color.RED);
					
				Line markerLineLeft = new Line(markerLX-viewPortX, mouseY+ovalValue-viewPortY, markerLX-viewPortX, 600);
				Line markerRightLeft = new Line(markerRX-viewPortX, mouseY+ovalValue-viewPortY, markerRX-viewPortX, 600);
					
				markerLineLeft.getStrokeDashArray().addAll(5.0,5.0);
				markerRightLeft.getStrokeDashArray().addAll(5.0,5.0);
				markerLineLeft.setStroke(Color.RED);
				markerRightLeft.setStroke(Color.BLUE);
					
				theDrawingPane.getChildren().addAll(rightMarker,leftMarker,markerLineLeft,markerRightLeft,rightText,leftText);

			}
			else if (isPure==true) {
				int pureTextOffset = 5;
				Text pureText = new Text(x-pureTextOffset, y-8, "Pf");
				pureText.setStroke(Color.BLACK);
				theDrawingPane.getChildren().add(pureText);
			}
		}
		pointPlacer();
	}
/** 
 * <h1>Description</h1>
 * Method returns the fraction result if the {@link #isOnMixedPhase(int, int)} passes<code>true</code>,then assigns the values to {@link #rightFrac} and {@link #leftFrac}
 * else return 0 if {@link #isOnPurePhase(int, int)}passes <code>true</code>.
 * @param mouseXinput {@link Integer} 
 * @param mouseYinput {@link Integer} 
 * @return {@link Void}
 * @see #isOnMixedPhase(int, int)
 * @see #isOnPurePhase(int, int)
 */
	public void fractionReturn(int mouseXinput,int mouseYinput) {
		if (isOnMixedPhase(mouseXinput, mouseYinput)) {
			isPure = false;
			
			mouseX =  mouseXinput;
			mouseY =  mouseYinput;
			markerCurrentX = mouseXinput;

			int iR = returnFraction(mouseX, mouseY,true);
			int iL = returnFraction(mouseX, mouseY,false);

			markerRX = iR+mouseX;
			markerLX = mouseX-iL;

			double rightWeight = PhaseMath.returnRightWeight(markerRX, markerCurrentX, markerLX);
			rightFrac = rightWeight; // Export

			double leftWeight = PhaseMath.returnLeftWeight(markerRX, markerCurrentX, markerLX);
			leftFrac = leftWeight;	// Export

		}
		else if (isOnPurePhase(mouseXinput, mouseYinput)) {
			isPure = true;
			rightFrac = 0;
			leftFrac = 0;
		}
	}
/**
 * <h1>Description</h1>
 * Method returns <code>true</code> if the tested mouseX and mouseY color correspond to the {@link #purePhases} array declared.
 * @param mouseX {@link Integer}
 * @param mouseY {@link Integer}
 * @return {@link Boolean}
 * @see #purePhases
 */
	private boolean isOnPurePhase(int mouseX,int mouseY){
		boolean ans = false;
		int i = pxReader.getArgb(mouseX,mouseY);
		for (int j = 0; j < purePhases.length; j++) {
			if (i==purePhases[j]) {
					ans = true;
			}
		}
		return ans;
	}
/**
 * <h1>Description</h1>
 * Method returns <code>true</code> if the tested mouseX and mouseY color correspond to the {@link #mixedPhase} array declared.
 * @param mouseX {@link Integer}
 * @param mouseY {@link Integer}
 * @return {@link Boolean}
 * @see #mixedPhase
 */
	private boolean isOnMixedPhase(int mouseX,int mouseY){
		boolean ans = false;
		int i = pxReader.getArgb(mouseX,mouseY);
		for (int j = 0; j < mixedPhase.length; j++) {
			if (i==mixedPhase[j]) {
					ans = true;
			}
		}
		return ans;
	}
/**
 * <h1>Description</h1>
 * Tests the neighbour pixel and cross check it to the {@link #purePhases} list, if <code>false</code> adds 1 to countnumber and passes
 * to the next neighbour until it founds an element of {@link #purePhases} then return the result of count number.
 * If calculingRightFraction == false, it's calculating for the leftFraction.
 * @param mouseX {@link Integer}
 * @param mouseY {@link Integer}
 * @param calculatingRightFraction {@link Boolean}
 * @return {@link Integer}
 * @see #purePhases
 * @see #mixedPhase
 */
	private int returnFraction(int mouseX,int mouseY,boolean calculatingRightFraction){
		boolean b = true;
		int countNumber = 0;
		
		while(b==true){
		
			int x = mouseX;
			int y = mouseY;
			
			x = calculatingRightFraction ? mouseX++ : mouseX--; 
			
			int i = pxReader.getArgb(x, y);
			
			boolean hasFinished = false;
				for (int j = 0; j < purePhases.length; j++) {
					if (hasFinished==false) {
						if (i == purePhases[j]){
							hasFinished = true;
							b = false;
						}
						if(i != purePhases[j]){}
					}
				}
				countNumber++;
		}
		return countNumber;
	}
/**
 *<h1>Description</h1>
 *This Method is responsible for handling methods that are associated with {@link MouseEvent#} <code>Mouse Moved</code>.
 *@param e {@link MouseEvent}
 *@return {@link Void}
 *@see #containsGeometry(MouseEvent, ArrayList)
 *@see #moved_LineDrawer
 *@see #mouse_Linger
 */
	public void compositionTempIndicator_moved(MouseEvent e) {
		class keyGetter{
			public int getKey(ArrayList<Shape> theCollection,Shape shape){
				int key = 0;
				for (int i = 0; i < theCollection.size(); i++) {
					if (theCollection.get(i)==shape) {
						key = i;
					}
				}
				return key;
			}
		}
		int indexAnomaly = 0;
		Shape s = containsGeometry(e,geometryList,Color.WHITE);
		int indexI = new keyGetter().getKey(geometryList, s);
		if (indexI <= 7) {
			indexAnomaly = 1;
		}
		else if (indexI > 7) {
			indexAnomaly = 2;
		}
		
		infoAreaName.setText(points_column.getCellData(indexI+indexAnomaly));
		info_AboutArea.setText(note_column.getCellData(indexI+indexAnomaly));
		
		moved_LineDrawer(e);	
		mouse_Linger(e,3000);
	}
/**
 * <h1>Description</h1>
 * Method Responsible for drawing the cross line on the working space when the mouse moves over it.
 * @param e {@link MouseEvent}
 * 
 * @return {@link Void}
 */
	public void moved_LineDrawer(MouseEvent e) {

		int xPossition = (int) e.getX();
		int yPossition = (int) e.getY();
		
		if ((e.getX()>1000)|(e.getY()>600)) {
			errorMessage_lb.setText("Value Exceed Bound Error : Value exceeds bound");
		}
		else {
		
			double percentileCementite = PhaseMath.mouseX_To_comp(isZoom,xPossition,viewPortX);
			current_cementiteComp = Math.round(percentileCementite);
			current_ironComp = Math.round(Math.abs(percentileCementite-100));

			double temprature = PhaseMath.mouseY_To_temp(isZoom,yPossition,viewPortY);
			current_tempCent = temprature;
			current_tempFar = phaseMath.toFarhanite(temprature);
			
			returnPhaseName(xPossition, yPossition,current_phaseName,true);
			
			phaseName_move.setText(current_phaseName.get());
			
			centigrade_move.setText(Double.toString(current_tempCent));
			farhanite_move.setText(Double.toString(current_tempFar));
			ironComp_move.setText(Double.toString(current_ironComp));
			cemmentiteComp_move.setText(Double.toString(current_cementiteComp));
			
			theDrawingPane.getChildren().removeAll(clickedLine1,clickedLine2,movedLine1,movedLine2,r1);
			
			movedLine1 = new Line(0,yPossition,1000,yPossition);
			movedLine2 = new Line(xPossition,0,xPossition,600);
			movedLine1.getStrokeDashArray().addAll(10.0,10.0);
			movedLine2.getStrokeDashArray().addAll(10.0,10.0);
			
			movedLine1.setStrokeDashOffset(100);
			movedLine1.setStroke(Color.DIMGRAY);
			movedLine2.setStroke(Color.DIMGRAY);
			
			movedLine1.setStrokeWidth(3);
			movedLine2.setStrokeWidth(3);
			
			int offset = 6;
			r1 = new Rectangle(xPossition-offset, yPossition-offset, offset*2, offset*2);
			r1.setFill(null);
			r1.setStroke(Color.DIMGRAY);
			r1.setStrokeWidth(2);
			
			theDrawingPane.getChildren().addAll(movedLine1,movedLine2,r1);
			
			temp_progIndic.setProgress(phaseMath.tempProg_Conv(current_tempCent));
			comp_progIndic.setProgress(phaseMath.comp_Conv(current_ironComp));
			
			double d = Math.abs(1-phaseMath.comp_Conv(current_ironComp));
			carbon_progIndic.setProgress(d);
			String s = Double.toString(d*6.67);
			
			if (s.length()>4) {
				carbon_comp_move.setText( s.substring(0, 4)+ "%");
			}
			else{
				carbon_comp_move.setText(s+ "%");
			}
		}
	}
/**
 * <h1>Description</h1>
 * Method responsible for locating coordinate point for the textArea invoked when the {@link #mouse_Linger(MouseEvent)}
 * So the text area will be displayed without shadowing other elements.
 * @param e {@link MouseEvent}
 * @return {@link Void}
 * @see #mouse_Linger(MouseEvent)
 */
	public void textAreaInfo_Starting_coOrdinate(MouseEvent e){
		double offset = 10;
		double posX = e.getX();
		double posY = e.getY();
		
		if ((posX>1000)|(posY>600)) {
			theDrawingPane.getChildren().remove(info_AboutArea);
		}
		if ((posX<1000)|(posY<600)) {
			
			if ((posX<500)&(posY<300)) {
				mouseX_Linger = posX + offset;
				mouseY_Linger = posY+ offset;
			}
			if ((posX>500)&(posY<300)) {
				mouseX_Linger = posX-infoArea_size_X- offset;
				mouseY_Linger = posY+offset;
			}
			if ((posX<500)&(posY>300)) {
				mouseX_Linger = posX + offset;
				mouseY_Linger = posY-infoArea_size_Y- offset;
			}
			if ((posX>500)&(posY>300)) {
				mouseX_Linger = posX-infoArea_size_X - offset;
				mouseY_Linger = posY-infoArea_size_Y - offset;
			}
		}
	}
	private HashMap< Integer, Integer> list = new HashMap<>();
/**
 * <h1>Description</h1>
 * Trigers an information text area after the mouse lingers for a <code>lingerPeriod</code> Millisecond
 * The time is referenced by the time line that is envoked when the program begins.
 * @param e {@link MouseEvent}
 * @param lingerPeriod {@link Integer}
 * @return {@link Void}
 * @see #timeLine_Counter(int)
 * @see #textAreaInfo_Starting_coOrdinate(MouseEvent)
*/
	public void mouse_Linger(MouseEvent e,int lingerPeriod) {
		textAreaInfo_Starting_coOrdinate(e);
		
		list.put(timeLine, (int)e.getX());
		if (haveLingered==true) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					theDrawingPane.getChildren().remove(infoAreaContainer);
				}
			});
			haveLingered=false;
		}
		t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(lingerPeriod);
								if (list.get(timeLine)==list.get(timeLine+(lingerPeriod/1000))) {
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											if (theDrawingPane.getChildren().contains(infoAreaContainer)==false) {
												theDrawingPane.getChildren().add(infoAreaContainer);
												infoAreaContainer.setLayoutX(mouseX_Linger);
												infoAreaContainer.setLayoutY(mouseY_Linger);
											}
										}
									});
									haveLingered = true;
								}
				} catch (InterruptedException e) {System.out.println("Interrputed");;}
			}
		});
		t1.start();
	}
/**
 * <h1>Description</h1>
 * From a textField Array disables the last two and clears their value while leaving the first intact and enabled.
 * @param textField_List {@link TextField}
 * @return {@link Void}
 */
	public void disable_target_components(TextField[] textField_List){
		textField_List[0].setDisable(false);
		for (int i = 1; i < textField_List.length; i++) {
			textField_List[i].setDisable(true);
			textField_List[i].clear();
		}
	}
/** 
 * <h1>Description</h1>
 * Responsible for assigning {@link #current_phaseName} and {@link #phaseName} to the phases names based on the color test first performed. 
 * The Method is conditional whether the "isMoving" value is true or false which assigns the bottom right corner values if it is true.
 * @param x - MouseX Co-Ordinate {@link Integer}
 * @param y - MouseY Co-Ordinate {@link Integer}
 * @param phaseNameValue {@link SimpleStringProperty}
 * @param isMoving {@link Boolean}
 * @return {@link Void}
 */
	public ArrayList<String> returnPhaseName(int x,int y,SimpleStringProperty phaseNameValue,boolean isMoving){
		int modifiedX = isZoom ? x+viewPortX: x;
		int modifiedY = isZoom ? y+viewPortY : y;
//		int modifiedX = x;
//		int modifiedY = y;
//		
		int test = 0;
		try {
			test = pxReader.getArgb(modifiedX,modifiedY);
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Index out of Bound");
			e.printStackTrace();
			System.out.println("X is " + x);
			System.out.println("Y is " + y);
			System.out.println("View port x is " + viewPortX);
			System.out.println("View port y is " + viewPortY);
		}
		ArrayList<String> neighbourNames = new ArrayList<>();
		if (!(isCoordinateContainedInGeometry(modifiedX,modifiedY, geometryList))) {
			switch (test) {
			
			case LIQUID_CODE			   : infoAboutAreaSetter(PhaseData.LIQUID.getPhaseName(),PhaseData.LIQUID.getPhaseNote(),phaseNameValue,isMoving);
			neighbourNames = PhaseData.LIQUID.getNeighbors();
			break;
			case DELTA_LIQUID_CODE  	   : infoAboutAreaSetter(PhaseData.DELTA_LIQUID.getPhaseName(),PhaseData.DELTA_LIQUID.getPhaseNote(),phaseNameValue,isMoving);				
			neighbourNames = PhaseData.DELTA_LIQUID.getNeighbors();
			break;
			case DELTA_CODE   			   : infoAboutAreaSetter(PhaseData.DELTA.getPhaseName(),PhaseData.DELTA.getPhaseNote(),phaseNameValue,isMoving);								
			neighbourNames = PhaseData.DELTA.getNeighbors();
			break;
			case DELTA_AUSTENITE_CODE  	   : infoAboutAreaSetter(PhaseData.DELTA_AUSTENITE.getPhaseName(),PhaseData.DELTA_AUSTENITE.getPhaseNote(),phaseNameValue,isMoving);			
			neighbourNames = PhaseData.DELTA_AUSTENITE.getNeighbors();
			break;
			case AUSTENITE_LIQUID_CODE     : infoAboutAreaSetter(PhaseData.AUSTENITE_LIQUID.getPhaseName(),PhaseData.AUSTENITE_LIQUID.getPhaseNote(),phaseNameValue,isMoving);		
			neighbourNames = PhaseData.AUSTENITE_LIQUID.getNeighbors();
			break;
			case LIQUID_CEMMENTITE_CODE    : infoAboutAreaSetter(PhaseData.LIQUID_CEMMENTITE.getPhaseName(),PhaseData.LIQUID_CEMMENTITE.getPhaseNote(),phaseNameValue,isMoving);		
			neighbourNames = PhaseData.LIQUID_CEMMENTITE.getNeighbors();
			break; 
			case AUSTENITE_CODE 		   : infoAboutAreaSetter(PhaseData.AUSTENITE.getPhaseName(),PhaseData.AUSTENITE.getPhaseNote(),phaseNameValue,isMoving);						
			neighbourNames = PhaseData.AUSTENITE.getNeighbors();
			break; 
			case AUSTENITE_CEMMENTITE_CODE : infoAboutAreaSetter(PhaseData.AUSTENITE_CEMMENTITE.getPhaseName(),PhaseData.AUSTENITE_CEMMENTITE.getPhaseNote(),phaseNameValue,isMoving);
			neighbourNames = PhaseData.AUSTENITE_CEMMENTITE.getNeighbors();
			break;
			case FERRITE_AUSTENITE_CODE    : infoAboutAreaSetter(PhaseData.FERRITE_AUSTENITE.getPhaseName(),PhaseData.FERRITE_AUSTENITE.getPhaseNote(),phaseNameValue,isMoving);		
			neighbourNames = PhaseData.FERRITE_AUSTENITE.getNeighbors();
			break; 
			case FERRITE_CODE 			   : infoAboutAreaSetter(PhaseData.FERRITE.getPhaseName(),PhaseData.FERRITE.getPhaseNote(),phaseNameValue,isMoving);							
			neighbourNames = PhaseData.FERRITE.getNeighbors();
			break; 
			case FERRITE_CEMMENTITE_CODE   : infoAboutAreaSetter(PhaseData.FERRITE_CEMMENTITE.getPhaseName(),PhaseData.FERRITE_CEMMENTITE.getPhaseNote(),phaseNameValue,isMoving);	
			neighbourNames = PhaseData.FERRITE_CEMMENTITE.getNeighbors();
			break; 
			case CEMMENTITE_CODE		   : infoAboutAreaSetter(PhaseData.CEMMENTITE.getPhaseName(),PhaseData.CEMMENTITE.getPhaseNote(),phaseNameValue,isMoving);						
			neighbourNames = PhaseData.CEMMENTITE.getNeighbors();
			break; 
			case IRON_CODE  			   : infoAboutAreaSetter(PhaseData.IRON.getPhaseName(),PhaseData.IRON.getPhaseNote(),phaseNameValue,isMoving);									
			neighbourNames = PhaseData.IRON.getNeighbors();
			break; 

			default: errorMessage_lb.setText("Cursor not on diagram");
			break;
			}
		}
		return neighbourNames;
	}
/**
 * @param phaseNameValue
 * @param isMoving
 */
	public void infoAboutAreaSetter(String phaseName,String phaseNameInfoResource,SimpleStringProperty phaseNameValue, boolean isMoving) {
		phaseNameValue.set(phaseName); 
		if (isMoving) {
			info_AboutArea.setText(phaseNameInfoResource);
			infoAreaName.setText(phaseName);
		}
	}
	public String getDataName(){
		return dataName;
	}
	public String getUserNote(){
		return userNote.getText();
	}
	public String getDataDate(){
		return timeDisplayer.getText();
	}
	public String getLeftElement(){
		return leftElement.getText();
	}
	public String getRightElement(){
		return rightElement.getText();
	}
	public String getPhaseDescription(){
		return phaseDescription.getText();
	}
	public String getPhaseDiagramName(){
		String diagramName = "The " + leftElement.getText() + " - " + rightElement.getText() + " " + elementFormulas.getText() + " Phase Diagram";
		return diagramName;
	}

}
