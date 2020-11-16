**Steps to see the required behaviour**
1. Run the PasswordWrite.java with the file argument
2. Run the PasswordRead.java with the same file argument: Here you will see that before any modification the password file is read successfully
3. You can check the content of serialized file by executing 'od -c <filename>'
4. Now run the shell script '**sudo ./ModifyIt.sh**' that modifies the password and writes it to a modified.ser file
5. If you execute od -c for both the files you can see the modification.
6. Now execute 'cp modified.ser <original.ser>' so that the corrupted password is written to the original file
6. Execute the PasswordWrite.java with original file. The password will be blank.