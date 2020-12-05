package ire.view;

import ire.Main;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameView implements LanguageControls {

  public static final String LANGUAGE_FOLDER = "languages/";
  public static final String DEFAULT_STARTING_LANGUAGE = "English";
  public static final String STARTING_STYLESHEET = "Default.css";
  public static final String STYLESHEETS_FOLDER = "stylesheets/";
  public static final Font DEFAULT_FONT_TITLE = new Font(26);
  public static final Font DEFAULT_FONT_DESCRIPTION = new Font(14);
  public static final Paint DEFAULT_BACKGROUND = Color.AZURE;

  private ErrorPrinting errorPrinting;
  private ResourceBundle languageResources;
  private ButtonsMaintainer buttonsMaintainer;
  private String language = DEFAULT_STARTING_LANGUAGE;


  public GameView() {
    errorPrinting = new ErrorPrinting(this);
    buttonsMaintainer = new ButtonsMaintainer();
    try {
      languageResources = ResourceBundle.getBundle(
          Main.DEFAULT_RESOURCES_PACKAGE + LANGUAGE_FOLDER + language);
    } catch (MissingResourceException e) {
      errorPrinting.printErrorMessageAlert("missingResourceException",
          "Language Resources");
    }
  }

  /**
   * @param width  - width of new scene
   * @param height -  height of new scene
   * @return - starting scene with the default css that appears in the center of the screen
   */
  public Optional<Scene> makeAnInitialScene(int width, int height) {
    try {
      String topTextContent = languageResources.getString("startingMessage");
      Text topText = new Text(topTextContent);
      topText.setFont(DEFAULT_FONT_TITLE);
      topText.setFill(Color.CORAL);
      //BorderPane.setAlignment(topText, Pos.TOP_CENTER);
      BorderPane displayLayout = new BorderPane(getGameDescription(), topText,
          null, null, buttonsMaintainer.createEnergyOptions());
      return Optional.of(uploadCSSFile(width, height, STARTING_STYLESHEET,
          displayLayout));
    } catch (MissingResourceException e) {
      errorPrinting.printErrorMessageAlert("missingResourceException",
          "Language Resources");
    }
    return Optional.empty();
  }

  /*
   * Returns a description of the game to make the center of the starting screen
   */
  private Text getGameDescription() {
    //initialInputBoxes.setAlignment(Pos.TOP_CENTER);
    String textContent = languageResources.getString("gameExplanation");
    Text description = new Text(textContent);
    description.setFont(DEFAULT_FONT_DESCRIPTION);
    description.setFill(Color.CORAL);
    return description;

  }

  /**
   * @param width   - width of returned scene
   * @param height  - height of returned scene
   * @param cssFile - string with name of wanted css file
   * @return - scene with a new style sheet
   */
  public Scene uploadCSSFile(int width, int height, String cssFile, BorderPane displayLayout) {
    Scene scene = new Scene(displayLayout, width, height, DEFAULT_BACKGROUND);
    try {
      scene.getStylesheets()
          .add(getClass().getResource(Main.DEFAULT_RESOURCE_FOLDER +
              STYLESHEETS_FOLDER + cssFile).toExternalForm());
    } catch (NullPointerException e) {
      errorPrinting.printErrorMessageAlert("CSSNotFound", cssFile);
    }
    return scene;
  }

  @Override
  public String getLanguage() {
    return language;
  }

}
