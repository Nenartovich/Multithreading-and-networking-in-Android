package com.nenartovich.task1_1.data

import com.nenartovich.task1_1.R

class Resources {
    companion object {
        val imageResources = mapOf(
            0 to R.drawable.picture1,
            1 to R.drawable.picture2,
            2 to R.drawable.picture3,
            3 to R.drawable.picture4,
            4 to R.drawable.picture5
        )

        val titles = mapOf(
            0 to "In the Car",
            1 to "Drowning Girl",
            2 to "Campbell's Soup Cans")

        val descriptions = mapOf(
            0 to "In the Car (sometimes Driving) is a 1963 " +
                    "pop art painting by Roy Lichtenstein. The smaller, " +
                    "older of the two versions of this painting formerly " +
                    "held the record for highest auction price for a Lichtenstein painting. " +
                    "The larger version has been in the collection of the Scottish National " +
                    "Gallery of Modern Art in Edinburgh since 1980.",
            1 to "Drowning Girl (also known as Secret Hearts or I Don't Care! I'd Rather Sink) " +
                    "is a 1963 painting in oil and synthetic polymer paint on canvas " +
                    "by Roy Lichtenstein, based on original art by Tony Abruzzo. " +
                    "The painting is considered among Lichtenstein's most significant works, " +
                    "perhaps on a par with his acclaimed 1963 diptych Whaam!. One of the most " +
                    "representative paintings of the pop art movement, Drowning Girl was " +
                    "acquired by the Museum of Modern Art in 1971.",
            2 to "Campbell's Soup Cans[1] (sometimes referred to as 32 Campbell's Soup Cans) " +
                    "is a work of art produced between November 1961 and March or April 1962, " +
                    "by American artist Andy Warhol. It consists of thirty-two canvases, each " +
                    "measuring 20 inches (51 cm) in height × 16 inches (41 cm) in width and " +
                    "each consisting of a painting of a Campbell's Soup can—one of each of " +
                    "the canned soup varieties the company offered at the time. " +
                    "The non-painterly works were produced by a screen printing process " +
                    "and depict imagery deriving from popular culture and " +
                    "belong to the pop art movement."
        )
    }
}