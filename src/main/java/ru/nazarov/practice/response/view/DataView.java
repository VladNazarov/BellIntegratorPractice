package ru.nazarov.practice.response.view;

/**
 * DTO ответа, в который оборачиваются все объекты
 */
public class DataView {
    /**
     * Данные
     */
    private Object data;

    public DataView(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
