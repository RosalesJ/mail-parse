-- DEFINE FILE OUTPUT FUNCTION
on write_to_file(this_data, target_file, append_data)
	try
		set the target_file to the target_file as string
		set the open_target_file to open for access file target_file with write permission
		
		if append_data is false then set eof of the open_target_file to 0
		
		write this_data & "
" to the open_target_file starting at eof
		close access the open_target_file
		
		return true
		
	on error error_message number error_number
		
		set this_error to "Error: " & error_number & ". " & error_message & return
		set the log_file to ((path to documents folder) as string) & "Script Error Log"
		
		my write_to_file(this_error, log_file, true)
		
	end try
end write_to_file

-- BEGIN MAIN CODE BLOCK
on run
	
	tell application "Mail"
		try
			set outFilePath to (((path to documents folder) as string) & "spamAdresses.txt")
			repeat with accout in imap accounts
				if accout's name is "IridianSmaster" then
					set accnt to accout
				end if
			end repeat
			
			repeat with box in accnt's mailboxes
				if box's name is "Spam" then
					set spam to box
				end if
			end repeat
			
			set spam_messages to spam's messages
			
			repeat with eachMessage in (a reference to spam_messages)
				set sentDate to "[" & (date received of eachMessage) & "] " as string
				set rawAddress to extract address from (sender of eachMessage)
				set toWrite to sentDate & rawAddress as rich text
				
				my write_to_file(toWrite, outFilePath, true)
			end repeat
			
			repeat with eachMessage in spam_messages
				set read status of eachMessage to true
			end repeat
			
			repeat with eachMessage in spam_messages
				delete eachMessage
			end repeat
		end try
		
	end tell
end run
