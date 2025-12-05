@echo off
REM Build and Download Dependencies for Film Rental App
REM This script will download all required dependencies including Caffeine

echo ===============================================
echo Film Rental App - Dependency Download Script
echo ===============================================
echo.
echo This script will:
echo 1. Clean previous build artifacts
echo 2. Download all Maven dependencies
echo 3. Compile the project
echo 4. Resolve all import errors
echo.
echo ===============================================

cd /d "C:\Users\dleenava\IdeaProjects\Film_RentalApp"

echo.
echo Downloading dependencies and compiling...
echo.

mvnw.cmd clean compile

echo.
echo ===============================================
echo Build Complete!
echo ===============================================
echo.
echo All dependencies have been downloaded.
echo The project should now compile without errors.
echo.
echo If errors still appear:
echo 1. Close your IDE
echo 2. Run this script again
echo 3. Open IDE again
echo.
pause

