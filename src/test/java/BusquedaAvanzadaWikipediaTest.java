import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/****************************************/
/*  Historia de Usuario: Como usuario de Wikipedia, quiero tener la capacidad de realizar búsquedas avanzadas para refinar
    los resultados y encontrar información más específica.

    Casos de Prueba:

    Caso de Prueba 1: Búsqueda con Palabra Clave

    Precondiciones:
    - Navegador web abierto y acceso a Internet.

    Pasos:
    1.  Ingresar a la pagina de Wikipedia.
    2.  Ingresar una palabra clave en el cuadro de búsqueda de Wikipedia, por ejemplo, "Inteligencia Artificial".
    3.  Presionar la tecla "Enter".

    Resultado Esperado:
    -   Los resultados de la búsqueda deben incluir artículos relacionados con "Inteligencia Artificial". La primera página
    de resultados debe contener al menos un enlace a un artículo relevante.


    Caso de Prueba 2: Búsqueda sin Resultados

    Precondiciones:
    -   Navegador web abierto y acceso a Internet.

    Pasos:
    1.  Ingresar a la pagina de Wikipedia.
    2.  Ingresar una palabra o frase que no arroje resultados, por ejemplo, "wxyz1234".
    3.  Presionar la tecla "Enter".

    Resultado Esperado:
    -   La página de resultados debe indicar claramente que no se encontraron resultados para la búsqueda "wxyz1234".
    Debería mostrarse un mensaje informativo al usuario.
 */
/****************************************/

public class BusquedaAvanzadaWikipediaTest {

    private WebDriver driver;

    @BeforeTest
    public void setDriver() throws Exception{
        String path = "/Users/chrisalanapazaaguilar/Documents/SIS-312/TallerAutomatizacion/chromedriver";
        System.setProperty("webdriver.chrome.driver", path);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }

    @Test
    public void busquedaPalabraClaveWikipedia(){
        /**************Preparacion de la prueba***********/

        //  1.  Ingresar a la pagina de Wikipedia
        String wikipediaUrl = "https://es.wikipedia.org";
        driver.get(wikipediaUrl);

        /**************Logica de la prueba***************/
        //  2. Ingresar una palabra clave en el cuadro de búsqueda de Wikipedia, por ejemplo, "Inteligencia Artificial".

        /*Capturar el campo de busqueda*/

        WebElement campoBusqueda = driver.findElement(By.xpath("//input[@id='searchInput']"));

        campoBusqueda.sendKeys("Inteligencia Artificial");

        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        WebElement botonBuscar = driver.findElement(By.xpath("//form[@id='searchform']//button"));

        //    3.  Presionar la tecla "Enter".
        botonBuscar.click();

        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        /************Verificacion de la situacion esperada - Assert***************/
        WebElement resultado = driver.findElement(By.xpath("//h1[@id='firstHeading']//span[@class='mw-page-title-main']"));
        String label = resultado.getText();
        System.out.println("Texto del resultado: "+label);

        Assert.assertEquals(label,"Inteligencia artificial");
    }

    @Test
    public void busquedaSinResultadosWikipedia(){
        /**************Preparacion de la prueba***********/

        //  1.  Ingresar a la pagina de Wikipedia
        String wikipediaUrl = "https://es.wikipedia.org";
        driver.get(wikipediaUrl);

        /**************Logica de la prueba***************/
        //  2.  Ingresar una palabra o frase que no arroje resultados, por ejemplo, "wxyz1234".

        /*Capturar el campo de busqueda*/

        WebElement campoBusqueda = driver.findElement(By.xpath("//input[@id='searchInput']"));

        campoBusqueda.sendKeys("wxyz1234");

        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        WebElement botonBuscar = driver.findElement(By.xpath("//form[@id='searchform']//button"));

        //    3.  Presionar la tecla "Enter".
        botonBuscar.click();

        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        /************Verificacion de la situacion esperada - Assert***************/
        WebElement resultado = driver.findElement(By.xpath("//div[@class='mw-search-results-info']//p[@class='mw-search-nonefound']"));
        String label = resultado.getText();
        System.out.println("Texto del resultado: "+label);

        Assert.assertEquals(label,"No hay resultados que cumplan los criterios de búsqueda. Puedes probar a buscar en las Wikipedias en otros idiomas.");
    }
}



