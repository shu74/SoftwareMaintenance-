/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.DiamondHunter.TileMapEditor;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Ashfaaq
 */
public class TileMapEditorController implements Initializable{

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        

    GraphicsContext g = canvasTile.getGraphicsContext2D();
    loadTiles("/Tilesets/testtileset.gif");
    loadItems("/Sprites/items.gif");
    loadMap("/Maps/testmap.map");
    
        draw(g);
        g.drawImage(
                itemss[0],
                save_yBoat,save_xBoat
        );
        g.drawImage(
                itemss[1],
                save_yAxe,save_xAxe
        );
        
        //reset everything
        yAxe.setText("");
        xAxe.setText("");
        yBoat.setText("");
        xBoat.setText("");    
    
        canvasTile.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                GraphicsContext gg = canvasTile.getGraphicsContext2D();
                if(ItemSelect==0){
                    draw(gg);
                    if(first_boat){
                        gg.drawImage(
                                itemss[0],
                                save_yBoat,save_xBoat
                        );
                    }
                    if(!first_boat){
                        gg.drawImage(
                                itemss[0],
                                x_Boat*16,y_Boat*16
                        );
                    }
                    x_Axe=(int)e.getX()/16;
                    y_Axe=(int)e.getY()/16;
                    xAxe.setText(Integer.toString(x_Axe));
                    yAxe.setText(Integer.toString(y_Axe));
                    first_axe=false;
                    gg.drawImage(
                            itemss[1],
                            x_Axe*16,y_Axe*16
                    );
                }
                else{
                    draw(gg);
                    if(first_axe){
                        gg.drawImage(
                                itemss[1],
                                save_yAxe,save_xAxe
                        );
                    }
                    if(!first_axe) {
                        gg.drawImage(
                                itemss[1],
                                x_Axe * 16, y_Axe * 16
                        );
                    }
                    x_Boat=(int)e.getX()/16;
                    y_Boat=(int)e.getY()/16;
                    xBoat.setText(Integer.toString(x_Boat));
                    yBoat.setText(Integer.toString(y_Boat));
                    first_boat=false;
                    gg.drawImage(
                            itemss[0],
                            x_Boat*16,y_Boat*16
                    );
                }
            }
        });
   
    }
    
    
       
        
     
    @FXML
    public void saveAxe(){
        System.out.println(xAxe.getText()+","+yAxe.getText());
        writeFileTile wrF = new writeFileTile("Axe.txt", xAxe.getText() , yAxe.getText());
        wrF.write();
    }
    
    @FXML
    public void saveBoat(){
        writeFileTile wrF = new writeFileTile("Boat.txt", xBoat.getText() , yBoat.getText());
        wrF.write();
    }
    
    @FXML
    public void setBoat(){
        ItemSelect = 1;
    }
    @FXML
    public void exit(){
        Stage stage = (Stage) Exit.getScene().getWindow();
          // do what you have to do
        stage.close();
    }
    
    @FXML
    public void run(){
        Stage stage = (Stage) runGame.getScene().getWindow();
        
    }
    
    @FXML
    public void setAxe(){
        ItemSelect = 0;
    }
    
    //setting the reset button
     @FXML
    public void reset(){
        yAxe.setText("");
        xAxe.setText("");
        yBoat.setText("");
        xBoat.setText(""); 
    }
    
    //loading and drawing maps
    public void loadItems(String s) {
        Image setTile = new Image(s);
        itemss = new Image[2];
        for (int col = 0; col < 2; col++) {
            itemss[col] = new WritableImage(
                    setTile.getPixelReader(),
                    col * tileSize,
                    16,

                    tileSize,
                    tileSize);
        }
    }
    public void loadTiles(String s) {

        try {

            Image setTile=new Image(s);
            numTilesAcross = (int) setTile.getWidth() / tileSize;
            tiles = new Image[2][numTilesAcross];

            for(int col = 0; col < numTilesAcross; col++) {
                tiles[0][col] = new WritableImage(
                        setTile.getPixelReader(),
                        col * tileSize,
                        0,
                        tileSize,
                        tileSize);
                tiles[1][col] = new WritableImage(
                        setTile.getPixelReader(),
                        col * tileSize,
                        tileSize,
                        tileSize,
                        tileSize);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void loadMap(String s) {

        try {

            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];

            String delims = "\\s+";
            for(int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for(int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void draw(GraphicsContext g) {

        for(int row = 0; row < 40; row++) {

            if(row >= numRows) break;

            for(int col = 0; col < 40; col++) {

                if(col >= numCols) break;
                if(map[row][col] == 0) continue;

                int rc = map[row][col];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;

                g.drawImage(
                        tiles[r][c],
                        col * tileSize,
                        row * tileSize
                );

            }

        }

        
    }
    
    
    
    private int x_Axe=26,y_Axe=37,x_Boat=12,y_Boat=4;
    public static int save_xAxe=416,save_yAxe=592,save_xBoat=192,save_yBoat=64;
    int ItemSelect=0;
    boolean first_boat=true,first_axe=true;

    private int[][] map;
    private int tileSize=16;
    private int numRows;
    private int numCols;

    private Image[][] tiles;
    private Image[] itemss;
    public Image image;
    private int numTilesAcross;
    
    @FXML
    public Canvas canvasTile;

    @FXML
    private TextField xAxe;

    @FXML
    private Button SAxe;
    
    @FXML
    private Button reset;

    @FXML
    private TextField yAxe;

    @FXML
    private TextField xBoat;

    @FXML
    private TextField yBoat;

    @FXML
    private Button SBoat;
    
    @FXML
    private Button Exit;
    
    @FXML
    private Button runGame;
    
}
