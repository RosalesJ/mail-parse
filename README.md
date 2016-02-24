A collection of scripts designed to empty the junk folder of one of my e-mail 
accounts on OS X mail.app after recording all the addresses.

==========================================================================================

SpamDeleter.scpt

      An AppleScript script that pulls the addresses off of each mail in the mailbox, 
      prints the addresses directly to a .txt file, and then deletes all the mails 
      in that specific mailbox.

mailParse.py

     A Python script that simplifies the format of the SpamDeleter.scpt output
     addresses. Pulls the addresses off of the existing address file, simplifies
     them, prints them to another file then wipes the original file.

Address.java 

     A Java class representing an e-mail address. I though it might be useful in 
     case I do any big data analysis in the future.

KillDuplicates.java 

    A result of an error in SpamDeleter.scpt causing there to be duplicates in its
    output file. It pulls the addresses off of the output file, converts them to a list of
    Java object addresses, sorts them by date, removes the duplicates and prints the remaining
    addresses onto a new file.
