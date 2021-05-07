package ru.nazarov.practice.response.view;

/**
 * DTO ошибок
 */
public class ErrorView {
    /**
     * Ошибка
     */
    private String error;

    public ErrorView(String error){
        this.error = error;
    }

    public ErrorView() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
