call runcrud
if "%ERRORLEVEL%" =="0" goto runtasks
echo Cannot run runcrud
goto fail

:runtasks
start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" =="0" goto end
echo I should install Chrome
goto fail

:fail
echo.
echo There were errors

:end
echo.
