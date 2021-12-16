import com.diffplug.gradle.spotless.SpotlessExtension

apply(plugin = "com.diffplug.spotless")

configure<SpotlessExtension> {
  kotlin {
    target("**/*.kt")

    ktlint(versions.ktLint).userData(
      // TODO this should all come from editorconfig https://github.com/diffplug/spotless/issues/142
      mapOf(
        "indent_size" to "2",
        "ij_kotlin_imports_layout" to "*",
        "end_of_line" to "lf",
        "charset" to "utf-8",
        "disabled_rules" to "no-wildcard-imports",
      )
    )

    trimTrailingWhitespace()
    indentWithSpaces()
    endWithNewline()
  }

  format("xml") {
    target("**/res/**/*.xml")

    trimTrailingWhitespace()
    indentWithSpaces(2)
    endWithNewline()
  }

  kotlinGradle {
    target("**/*.gradle.kts", "*.gradle.kts")

    ktlint(versions.ktLint).userData(
      mapOf(
        "indent_size" to "2",
        "ij_kotlin_imports_layout" to "*",
        "end_of_line" to "lf",
        "charset" to "utf-8",
        "disabled_rules" to "no-wildcard-imports",
      )
    )

    trimTrailingWhitespace()
    indentWithSpaces()
    endWithNewline()
  }
}