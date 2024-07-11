# Code Challenge

This application processes stock price CSV files, identifies outliers, and generates reports.

## Setup

1. Make sure you have Java JDK 8 or higher installed.

2. Clone the repository:
   - git clone [https://github.com/GheorgheLiviu/LSEG-Code_Challenge_240711](https://github.com/GheorgheLiviu/LSEG-Code_Challenge_240711)
   - cd LSEG-Code_Challenge_240711



3. Compile the Java files:
   javac -d . src/com/example/*.java



## Usage

1. Place your input CSV files in the `./data` directory.

2. Run the application:
   java com.example.PriceProcessor

3. Enter the number of files to process when prompted.

4. Check the `./output` directory for the results.

## Input Format

CSV files should have this format:
   - Stock-ID, Timestamp, Price
   - AAPL, 01-01-2023, 150.25



## Output

Output CSV files will contain:
- Stock-ID
- Timestamp
- Actual stock price
- Mean of 30 data points
- Deviation from mean
- Percentage deviation above threshold

## Error Handling

The application will display error messages in the console for issues like missing files or invalid data.