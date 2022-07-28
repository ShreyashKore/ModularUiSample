package com.shreyashkore.modularuisample.data

data class Book(
    val id: Long,
    val title: String,
    val author: String,
    val imageUri: String,
    val rating: Float,
    val description: String,
    val price: Int,
    val isAvailable: Boolean = true,
)


val SAMPLE_BOOKS = listOf(
    Book(
        1,
        "Harry Potter and the Philosopher's Stone",
        "J. K. Rowling",
        "harry-potter.jpg",
        4.2f,
        "Harry Potter and the Philosopher's Stone is a fantasy novel written by British author J. K. Rowling.",
        30
    ),
    Book(
        2,
        "To Kill a Mockingbird",
        "Harper Lee",
        "to-kill-a-mockingbird.jpg",
        4.5f,
        "To Kill a Mockingbird is a novel by the American author Harper Lee. It was published in 1960 and was instantly successful. In the United States, it is widely read in high schools and middle schools. To Kill a Mockingbird has become a classic of modern American literature, winning the Pulitzer Prize.",
        20
    ),
    Book(
        3,
        "The Great Gatsby",
        "F. Scott Fitzgerald",
        "the-great-gatsby.jpg",
        4.3f,
        "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. " +
                "Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
        24
    ),
    Book(
        4,
        "The Alchemist",
        "Paulo Coelho",
        "the-alchemist.jpg",
        3.5f,
        "The Alchemist details the journey of a young Andalusian shepherd boy named Santiago. Santiago, believing a recurring dream to be prophetic, decides to travel to the pyramids of Egypt to find treasure.",
        5
    ),
    Book(
        5,
        "Pride and Prejudice",
        "Jane Austen",
        "pride-and-prejudice.jpg",
        3.3f,
        "Pride and Prejudice, romantic novel by Jane Austen, published anonymously in three volumes in 1813.",
        7
    ),
    Book(
        6,
        "Become an Engineer Not Just an Engineering Graduate",
        "Sivakumar Palaniappan",
        "harry-potter.jpg",
        4.6f,
        "Become an Engineer, Not Just an Engineering Graduate, by Sivakumar Palaniappan, is a compelling book for engineering students and graduates, aimed at motivating them to be passionate about their careers.",
        20
    ),
    Book(
        7,
        "The First 90 Days",
        "Watkins",
        "harry-potter.jpg",
        4.5f,
        "Watkins gives you the keys to successfully negotiating your next move, whether you're onboarding into a new company, being promoted internally or embarking on an international assignment.",
        12
    ),
    Book(
        8,
        "Twilight",
        "Stephenie Meyer",
        "harry-potter.jpg",
        3.63f,
        "Deeply seductive and extraordinarily suspenseful, Twilight is a love story with bite.",
        12
    ),
    Book(
        9,
        "Jane Eyre",
        "Charlotte Brontë, Michael Mason",
        "harry-potter.jpg",
        4.5f,
        "Orphaned as a child, Jane has felt an outcast her whole young life. Her courage is tested once again when she arrives at Thornfield Hall, where she has been hired by the brooding, proud Edward Rochester to care for his ward Adèle. Jane finds herself drawn to his troubled yet kind spirit. She falls in love.",
        12
    ),
    Book(
        10,
        "It Ends with Us",
        "Colleen Hoover",
        "harry-potter.jpg",
        4.5f,
        "Lily hasn’t always had it easy, but that’s never stopped her from working hard for the life she wants.",
        12
    ),
    Book(
        11,
        "Divergent",
        "Veronica Roth",
        "harry-potter.jpg",
        4.5f,
        "In Beatrice Prior's dystopian Chicago world, society is divided into five factions, each dedicated to the cultivation of a particular virtue—Candor (the honest), Abnegation (the selfless), Dauntless (the brave), Amity (the peaceful), and Erudite (the intelligent).",
        12
    ),
    Book(
        12,
        "Where the Crawdads Sing",
        "Delia Owens",
        "harry-potter.jpg",
        4.5f,
        "For years, rumors of the “Marsh Girl” haunted Barkley Cove, a quiet fishing village. Kya Clark is barefoot and wild; unfit for polite society. So in late 1969, when the popular Chase Andrews is found dead, locals immediately suspect her.",
        12
    ),
    Book(
        13,
        "The Time Traveler's Wife",
        "Audrey Niffenegger",
        "harry-potter.jpg",
        4.5f,
        "A funny, often poignant tale of boy meets girl with a twist: what if one of them couldn't stop slipping in and out of time? Highly original and imaginative, this debut novel raises questions about life, love, and the effects of time on relationships.",
        12
    ),
    Book(
        14,
        "Thinking, Fast and Slow",
        "Daniel Kahneman",
        "harry-potter.jpg",
        4.5f,
        "In the highly anticipated Thinking, Fast and Slow, Kahneman takes us on a groundbreaking tour of the mind and explains the two systems that drive the way we think.",
        12
    ),
)