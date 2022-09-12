/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage Flo2.jpeg
 *
 *  @author: jacqueline-Lillie Tobias, jlt230
 *
 *************************************************************************/

import java.awt.Color;

import javax.swing.colorchooser.ColorChooserComponentFactory;

public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename) {
        this.collageDimension = 4;
        this.tileDimension = 100;
        this.original = new Picture(filename);
        this.collage = new Picture((collageDimension * tileDimension), (collageDimension * tileDimension));
        for (int cCol = 0; cCol < collage.width(); cCol++){
            for(int cRow = 0; cRow < collage.height(); cRow++){
                int scaledWidth = cCol * original.width() / (tileDimension * collageDimension);
                int scaledHeight = cRow * original.height() / (tileDimension * collageDimension);
                Color color = original.get(scaledWidth, scaledHeight);
                collage.set(cCol, cRow, color);
            }
        }
	    // WRITE YOUR CODE HERE
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename, int td, int cd) {
        this.collageDimension = cd;
        this.tileDimension = td;
        this.original = new Picture(filename);
        this.collage = new Picture((tileDimension * collageDimension), (tileDimension * collageDimension));
            for (int cCol = 0; cCol < collage.width(); cCol++){
                for(int cRow = 0; cRow < collage.height(); cRow++){
                    int scaledWidth = cCol * original.width() / (tileDimension * collageDimension);
                    int scaledHeight = cRow * original.height() / (tileDimension * collageDimension);
                    Color color = original.get(scaledWidth, scaledHeight);
                    collage.set(cCol, cRow, color);
                }
            }
            
        
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {
        return this.collageDimension;
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {
        return this.tileDimension;
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {
        return this.original;
	    // WRITE YOUR CODE HERE
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {
        return this.collage;
	    // WRITE YOUR CODE HERE
    }
    
    /*
     * Display the original image
     * Assumes that original has been initialized
     */
    public void showOriginalPicture() {
       this.original.show();
	    // WRITE YOUR CODE HERE
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() {
        this.collage.show();
	    // WRITE YOUR CODE HERE
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) {
        Picture replacedImage = new Picture(filename);
        Picture scaledTile = new Picture(tileDimension, tileDimension);
        for (int cCol = 0; cCol < scaledTile.width(); cCol++){
            for(int cRow = 0; cRow < scaledTile.height(); cRow++){
                int scaledWidth = cCol * replacedImage.width() / tileDimension;
                int scaledHeight = cRow * replacedImage.height() / tileDimension;
                Color color = replacedImage.get(scaledWidth, scaledHeight);
                scaledTile.set(cCol, cRow, color);
            }
        }
        int newCol = collageCol * tileDimension;
        int newRow = collageRow * tileDimension;
        for (int i = 0; i < tileDimension; i++){
            for (int j = 0; j < tileDimension; j++){
                Color color = scaledTile.get(i, j);
                collage.set(newCol + i, newRow + j, color);
            }
        }
	    // WRITE YOUR CODE HERE
    }
    
    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */
    public void makeCollage () {
    Picture temp = new Picture(tileDimension, tileDimension);
    for (int cCol = 0; cCol < collage.width(); cCol++){
        for(int cRow = 0; cRow < collage.height(); cRow++){
            int scaledWidth = cCol * temp.width() / collage.width();
            int scaledHeight = cRow * temp.height() / collage.height();
            Color color = collage.get(cCol, cRow);
            temp.set(scaledWidth, scaledHeight, color);
        }
    }
        for (int a = 0; a < collageDimension; a++){
            for (int b = 0; b < collageDimension; b++){
                for (int i = 0; i < tileDimension; i++){
                    for (int j = 0; j < tileDimension; j++){
                        Color color = temp.get(i , j);
                        collage.set((tileDimension * a) + i, (tileDimension * b) + j, color);
            }
        }
    }
}
   // WRITE YOUR CODE HERE
    }
    

    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see CS111 Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) {
        if (component.equals("green")){
            for (int i = (collageCol * tileDimension); i < ((collageCol * tileDimension) + tileDimension); i++){
                for (int j = (collageRow * tileDimension); j < ((collageRow *tileDimension) + tileDimension); j++){
                    Color toGreen = collage.get(i, j);
                    int green = toGreen.getGreen();    
                    Color greenNew = new Color(0, green, 0);
                    collage.set(i, j, greenNew);

                }
            }
        }
        else if (component.equals("blue")){
            for (int i = (collageCol * tileDimension); i < (( collageCol *tileDimension) + tileDimension); i++){
                for (int j = (collageRow * tileDimension); j < ((collageRow * tileDimension) + tileDimension); j++){
                    Color toBlue = collage.get(i, j);
                    int blue = toBlue.getBlue();    
                    Color blueNew = new Color(0, 0, blue);
                    collage.set(i, j, blueNew);

                }
            }

        }
        else{
            for (int i = (collageCol * tileDimension); i < ((tileDimension * collageCol) + tileDimension); i++){
                for (int j = (collageRow * tileDimension); j < ((collageRow * tileDimension) + tileDimension); j++){
                    Color toRed = collage.get(i, j);
                    int red = toRed.getRed();    
                    Color redNew = new Color(red, 0, 0);
                    collage.set(i, j, redNew);
                }
            }
           
        }
	    // WRITE YOUR CODE HERE
    }

    /*
     * Grayscale tile at (collageCol, collageRow)
     * (see CS111 Week 9 slides, the code for luminance is at the book's website)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */

    public void grayscaleTile (int collageCol, int collageRow) {
        for (int i = (collageCol * tileDimension); i < ((collageCol * tileDimension) + tileDimension); i++){
            for (int j = (collageRow * tileDimension); j < ((collageRow * tileDimension) + tileDimension); j++){
                Color greyColor = collage.get(i, j);
                Color grey = Luminance.toGray(greyColor);
                collage.set(i, j, grey);
            }
        }
	    // WRITE YOUR CODE HERE
    }


    /*
     *
     *  Test client: use the examples given on the assignment description to test your ArtCollage
     */
    public static void main (String[] args) {

        ArtCollage art = new ArtCollage(args[0]); 
        art.makeCollage();
        art.replaceTile(args[1], 0, 0);
        art.colorizeTile("green", 3, 3);
        art.grayscaleTile(2, 2);
        art.showCollagePicture();
        
    }
}
