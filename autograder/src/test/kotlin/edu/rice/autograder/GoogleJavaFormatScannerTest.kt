/*
 * RiceChecks
 * Copyright (c) 2019, Dan S. Wallach, Rice University
 * Available subject to the Apache 2.0 License
 */

package edu.rice.autograder

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GoogleJavaFormatScannerTest {
    @Test
    fun testReadDir() {
        val comp215BuildDir = readResourceDir("comp215-build").getOrFail().toList()
        assertEquals(6, comp215BuildDir.size)
        assertTrue(comp215BuildDir.contains("comp215-build/google-java-format"))
    }

    @Test
    fun testFileStatesExample() {
        val input = readResource("comp215-build/google-java-format/0.8/fileStates.txt").getOrFail()
        val testResult = googleJavaFormatParser(input).eval()
        assertFalse(testResult.passing)
    }

    @Test
    fun testCrazyGoogleJavaFormatDirectories() {
        val input = readFileWildcardDir(
            "src/test/resources/comp215-build/weird-google-java-format",
            "fileStates.txt"
        ).getOrFail()
        val testResult = googleJavaFormatParser(input).eval()
        assertFalse(testResult.passing)

        val input2 = readFileWildcardDir(
            "src/test/resources/comp215-build/weirder-google-java-format",
            "fileStates.txt"
        )
        assertTrue(input2.isFailure())
    }
}
