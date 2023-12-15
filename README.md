# ASCII Art Converter

This console-based application converts images into ASCII art, providing customization through various filters.

## Overview

The ASCII Art Converter is a simple yet powerful tool that allows you to transform images into ASCII art with ease. Whether you want to add a creative touch to your images or experiment with different visual effects, this application has got you covered.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [Basic Command](#basic-command)
  - [Filter Commands](#filter-commands)
  - [Output Commands](#output-commands)
  - [Ascii Table selection](#ascii-table-selection)
  - [Example Commands](#example-commands)

## Getting Started

### Prerequisites

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) installed on your machine.

### Installation

1. Clone the ASCII art repository to your local machine.
2. Navigate to the project directory.

## Usage

Inside the project directory, use the following commands:

## Start
```bash
sbt
```

### Basic Command

```bash
run --image "<path_to_image>"
```

### Filters Commands
```bash
 --brightness < (-100,100) for best results>

 --flip <(x or y)>

 --invert 
```
### Ascii Table Selection
table_name:
- DEFAULT_TABLE
- DENSE_TABLE
- SPARCE_TABLE
```bash
--table <table_name>

--custom-table "<chars>"
```


### Output Commands
```bash
--output-file "path"

--output-console
```

### Example Commands
```bash
# Convert image with brightness adjustment
run --image "images/example.jpg" --brightness +20 --output-file "output.txt"

# Convert image with horizontal flip
run --image "images/example.png" --flip x --output-file "output.txt"

# Convert image with vertical flip
run --image "images/example.jpg" --flip y --output-file "output.txt"

# Convert image with grayscale inversion with output to console
run --image "images/example.jpg" --invert --output-console

# Save ASCII art to a file
run --image "images/example.png" --output-file "output.txt"

# Chain filters
run --image "images/example.png" --flip x  
--brightness -30 --output-file "output.txt" --output-console

## Select pre-defined table
run --image "src/test/assets/bmw.jpg"  --table DENSE_TABLE --output-console

## Define custom table for ascii conversion
run --image "src/test/assets/bmw.jpg"  --custom-table "#&1}(*<:." --output-console
```



