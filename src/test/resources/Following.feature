Feature: Following feature

  Scenario: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions
    Given A Twitter console
    When "Alice" posts the messages
      | Yesterday 24 degrees... |
      | Today, 8!               |
    And "Bob" posts the messages
      | Loving this weather |
    And "Charlie" posts the messages
      | I'm in New York today! Anyone wants to have a coffee? |
    And "Charlie" follows "Alice"
    And "Charlie" follows "Bob"
    Then These messages appear on the console
      | Alice -> Yesterday 24 degrees...                                 |
      | Alice -> Today, 8!                                               |
      | Bob -> Loving this weather                                       |
      | Charlie -> I'm in New York today! Anyone wants to have a coffee? |
      | Charlie follows Alice                                            |
      | Charlie follows Bob                                              |
