Feature: Posting feature

  Scenario: Check when a user posts a message, it displays on the twitter console
    Given A Twitter console
    When "Alice" posts the message "LOL"
    Then The message "Alice -> LOL" appears on the console
