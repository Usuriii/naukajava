package pd15.dto;

public record CommandBookRequest(String isbn, String title, String author, int year, int availableCopies) {}
