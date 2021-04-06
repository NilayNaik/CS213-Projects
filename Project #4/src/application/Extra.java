package application;

/**
 * Represents all possible extras that someone can put on a sandwich
 * Gives a definitive list of extras along with their string representations
 * @author Evan Maggio, Nilay Naik
 */
public enum Extra {
    LETTUCE {
        public String toString () {
            return "lettuce";
        }
    },
    TOMATO {
        public String toString () {
            return "tomato";
        }
    },
    BACON {
        public String toString () {
            return "bacon";
        }
    },
    RED_ONION {
        public String toString () {
            return "red onions";
        }
    },
    WHITE_ONION {
        public String toString () {
            return "white onions";
        }
    },
    MUSHROOM {
        public String toString () {
            return "mushrooms";
        }
    },
    CHEDDAR {
        public String toString () {
            return "cheddar cheese";
        }
    },
    SWISS {
        public String toString () {
            return "swiss cheese";
        }
    },
    PEPPERS {
        public String toString () {
            return "peppers";
        }
    },
    MAYO {
        public String toString () {
            return "mayonnaise";
        }
    },
    CHIPOTLE_MAYO {
        public String toString () {
            return "chipotle mayonnaise";
        }
    },
    AVOCADO {
        public String toString () {
            return "avocado";
        }
    },
    CUCUMBER {
        public String toString () {
            return "cucumbers";
        }
    },
    BALSAMIC {
        public String toString () {
            return "balsamic vinaigrette";
        }
    }
}
