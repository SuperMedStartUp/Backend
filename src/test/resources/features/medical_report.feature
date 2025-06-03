Feature: Create medical report

  Scenario Outline: Create a report successfully
    Given the system is available
    When send a request to create a report with the data:
      | reason            | date       | patientId   |
      | <reason>          | <date>     | <patientId> |
    Then the system should respond with a 201 code
    And the report should contain the data:
      | reason            | date       | patientId   |
      | <reason>          | <date>     | <patientId> |

    Examples:
      |  reason           | date       | patientId   |
      | Consulta medica   | 2023-14-01 | 10          |