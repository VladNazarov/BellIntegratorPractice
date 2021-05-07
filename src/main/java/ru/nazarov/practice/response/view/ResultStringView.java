package ru.nazarov.practice.response.view;

/**
 * DTO ответа
 */
public class ResultStringView {
    private String result;

    public ResultStringView(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
