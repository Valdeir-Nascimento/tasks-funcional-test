package br.ce.wcaquino.tasks.funcional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao() throws MalformedURLException {
//        WebDriver driver = new ChromeDriver();
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.106:4444/wd/hub"), cap);
        driver.navigate().to("http://192.168.0.106:9999/tasks");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            //Clicar em add Todo
            driver.findElement(By.id("addTodo")).click();

            //Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2099");

            //Escrever a descrição
            driver.findElement(By.id("task")).sendKeys("Teste task selenium");

            //Clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //Validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Sucess!", mensagem);

        } finally {
            //Fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            //Clicar em add Todo
            driver.findElement(By.id("addTodo")).click();

            //Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2099");

            //Clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //Validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", mensagem);

        } finally {
            //Fechar o browser
            driver.quit();
        }
    }


    @Test
    public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            //Clicar em add Todo
            driver.findElement(By.id("addTodo")).click();

            //Escrever a descrição
            driver.findElement(By.id("task")).sendKeys("Teste task selenium");

            //Clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //Validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", mensagem);

        } finally {
            //Fechar o browser
            driver.quit();
        }
    }


    @Test
    public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            //Clicar em add Todo
            driver.findElement(By.id("addTodo")).click();

            //Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2009");

            //Escrever a descrição
            driver.findElement(By.id("task")).sendKeys("Teste task selenium");

            //Clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //Validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", mensagem);

        } finally {
            //Fechar o browser
            driver.quit();
        }
    }


}
