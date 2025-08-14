import java.awt.Color; 
import java.util.ArrayList;
import javafx.scene.control.Cell;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 

 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private static int cantidadDeBolasExistentes = 0;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate int numeroBolas bouncing balls
     */
    public void bounce(int numeroBolas)
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        ArrayList<BouncingBall> balls = new ArrayList<>(); // Lista con las bolas
        //Valores para el radio y diametro
        Random generadorRadio = new Random(); // Genera el radio aleatorio

        //Valores para el color
        Random generadorColor = new Random(); // Genera el color aleatorio
        
        //Valores para la posición de inicio
        Random generadorPosicion = new Random(); // Genera la posición de inicio aleatoria

        int i = 0;
        while (i < numeroBolas) {
            int diametro = 2 * (generadorRadio.nextInt(50) + 21);
            int red = generadorColor.nextInt(256);
            int green = generadorColor.nextInt(256);
            int blue = generadorColor.nextInt(256);
            int posicionX = generadorPosicion.nextInt(200) + 50;
            int posicionY = generadorPosicion.nextInt(200) + 50;
        
            Color colorAleatorio = new Color(red, green, blue);
            BouncingBall bola = new BouncingBall (posicionX, posicionY, diametro, colorAleatorio, ground, myCanvas);
            cantidadDeBolasExistentes++;
            bola.draw();
            balls.add(bola);
            i++;
        }

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            for (BouncingBall bolas : balls) {
                bolas.move();
                // stop once ball has travelled a certain distance on x axis
                if(bolas.getXPosition() >= 550) {
                    finished = true;
                }
            }
        }
    }
    
    public static int getCantidadDeBolasExistentes() {
        return cantidadDeBolasExistentes;
    }
}
