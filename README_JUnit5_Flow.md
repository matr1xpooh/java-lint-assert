# JUnit5 Flow Sequence Diagrams

This document contains sequence diagrams that illustrate the flow of the JUnit5 lint-assert processing.

## Diagrams Overview

### 1. Complete JUnit5 Flow (`junit5_flow_sequence_diagram.puml`)
This diagram shows the complete end-to-end flow of JUnit5 processing, including:
- Initialization and context building
- Class discovery and bytecode parsing
- Method annotation processing
- Test method identification
- Disabled method filtering
- Assert call filtering
- Result output

### 2. JUnit5 Strategy Specific Flow (`junit5_specific_flow.puml`)
This diagram focuses specifically on the JUnit5 strategy interactions, showing:
- How JUnit5 test methods are identified
- How disabled methods are detected
- How assert calls are filtered
- The JUnit5-specific configuration

## Key Components in the Flow

### JUnit5Strategy
- **Supported Framework**: `"Lorg/junit/jupiter/api/Test;"`
- **Assert API**: `"org.junit.jupiter.api"`
- **Disabled Annotation**: `"Lorg/junit/jupiter/api/Disabled;"`

### Processing Steps
1. **Class Discovery**: `TestClassFinder` scans the specified package for test classes
2. **Bytecode Parsing**: ASM visitors parse the bytecode to extract method information
3. **Test Identification**: Methods with `@Test` annotations are identified as test methods
4. **Disabled Filtering**: Methods with `@Disabled` annotations are excluded
5. **Assert Filtering**: Only method calls from `org.junit.jupiter.api` are kept
6. **Result Output**: Final results are formatted and returned

## How to Use These Diagrams

### Prerequisites
- PlantUML installed or online PlantUML server
- Any text editor or IDE that supports PlantUML

### Viewing the Diagrams
1. Open the `.puml` files in a PlantUML-compatible editor
2. Or use the online PlantUML server: http://www.plantuml.com/plantuml/
3. Copy the content and paste it into the online editor

### Example JUnit5 Test Class
```java
package sample.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class AssertJunit5Style {

    @Test
    void withAsserts() {
        assertTrue(true);
        Assertions.assertArrayEquals(new int[]{1,2}, new int[]{1,2});
    }

    @Test
    @Disabled
    void iAmDisabled(){
        throw new UnsupportedOperationException();
    }

    void iAmNotATest(){
        // This method will be filtered out
    }
}
```

## Flow Summary

The JUnit5 flow follows this pattern:
1. **Scan** for test classes in the specified package
2. **Parse** bytecode using ASM visitors to extract method metadata
3. **Identify** methods annotated with `@Test`
4. **Filter** out methods annotated with `@Disabled`
5. **Remove** non-assert method calls (keep only `org.junit.jupiter.api` calls)
6. **Output** the final results showing test methods and their assert calls

## Integration with Other Frameworks

The JUnit5 strategy extends `JUnit4Strategy` and inherits most of its behavior, only overriding the framework-specific configuration:
- Test annotation detection
- Assert API package identification
- Disabled annotation detection

This design allows for consistent processing across different JUnit versions while maintaining framework-specific configurations.
