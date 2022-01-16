SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE inventory_item;

INSERT INTO inventory_item (`id`, `product_name`, `price`, `slug`, `quantity`, `description`, `time`) VALUES
(1, "books", 200.0, "books", 4, "none", CURRENT_TIMESTAMP),
(2, "pen", 100.0, "pen", 5, "none", CURRENT_TIMESTAMP ),
(3, "pencil", 50.0, "pencil", 3, "none", CURRENT_TIMESTAMP ),
(4, "coca cola", 10.0, "coca_cola", 10, "none", CURRENT_TIMESTAMP ),
(5, "coca cola", 50.0, "coca_cola", 7, "none", CURRENT_TIMESTAMP ),
(6, "coca cola", 100.0, "coca_cola", 1, "none", CURRENT_TIMESTAMP ),
(7, "coca cola", 70.0, "coca_cola", 2, "none", CURRENT_TIMESTAMP );

SET FOREIGN_KEY_CHECKS = 1;
--     private String productName;
-- private BigDecimal price;
--
--     private String slug;
--     private Long quantity;
--     private String description;
--     private LocalDateTime time;