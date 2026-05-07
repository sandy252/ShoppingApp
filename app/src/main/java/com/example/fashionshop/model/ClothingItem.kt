package com.example.fashionshop.model

data class ClothingItem(
    val id: Int,
    val name: String,
    val brand: String,
    val price: Double,
    val category: String,
    val emoji: String,
    val description: String
)

object SampleData {
    val items = listOf(
        ClothingItem(1,  "Floral Midi Dress",     "Zara",        59.99,  "Women", "👗", "Light floral print, perfect for spring."),
        ClothingItem(2,  "Knit Oversized Blazer", "H&M",         79.99,  "Women", "🧥", "Cozy knit blazer, works all year."),
        ClothingItem(3,  "High-Rise Skinny Jean",  "Levi's",      69.99,  "Women", "👖", "Classic 5-pocket, high-rise silhouette."),
        ClothingItem(4,  "Satin Slip Skirt",       "ASOS",        39.99,  "Women", "👘", "Bias-cut satin, elegant for day or night."),
        ClothingItem(5,  "Ribbed Turtleneck",      "Uniqlo",      29.99,  "Women", "👚", "Ultra-soft ribbed cotton, timeless basic."),
        ClothingItem(6,  "Trench Coat",            "Burberry",   299.99,  "Women", "🧣", "Iconic double-breasted trench."),
        ClothingItem(7,  "Slim Chino Pants",       "Gap",         54.99,  "Men",   "👖", "Stretch chino, tapered leg."),
        ClothingItem(8,  "Oxford Button-Down",     "Ralph Lauren",79.99,  "Men",   "👔", "Classic Oxford weave, non-iron finish."),
        ClothingItem(9,  "Puffer Jacket",          "North Face", 149.99,  "Men",   "🧥", "700-fill down, waterproof shell."),
        ClothingItem(10, "Graphic Tee",            "Supreme",     45.99,  "Men",   "👕", "100% cotton, bold seasonal graphic."),
        ClothingItem(11, "Slim Jogger",            "Nike",        59.99,  "Men",   "🩲", "Tapered fleece jogger, zip pockets."),
        ClothingItem(12, "Denim Jacket",           "Levi's",      89.99,  "Men",   "🧤", "Classic trucker denim, stonewash finish."),
        ClothingItem(13, "Rainbow Hoodie",         "Carter's",    24.99,  "Kids",  "🌈", "Soft fleece, front kangaroo pocket."),
        ClothingItem(14, "Dino Printed Tee",       "OshKosh",     14.99,  "Kids",  "🦕", "Fun all-over dino print, tagless."),
        ClothingItem(15, "Cargo Shorts",           "Gap Kids",    19.99,  "Kids",  "🩳", "Multi-pocket cargo, adjustable waist."),
        ClothingItem(16, "Party Dress",            "Zara Kids",   34.99,  "Kids",  "🎀", "Tulle overlay, satin bow at the back.")
    )
}