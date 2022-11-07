package tests.ui.pages.base;


import static com.codeborne.selenide.Selenide.open;

public class BasePage {
    public void open_page(String url) {
        open(url);
    }
}
