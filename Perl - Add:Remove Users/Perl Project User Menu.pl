#!/usr/bin/perl -w

my $fileLocation = $ARGV[0];

while (1)
{
	printMenu();

	my $userInput = <STDIN>;
	chomp($userInput);

	if (uc($userInput) eq 'P')
	{
		choiceP();
	} 
	elsif (uc($userInput) eq 'A')
	{
		choiceA();
	} 
	elsif (uc($userInput) eq 'S')
	{
		choiceS();
	} 
	elsif (uc($userInput) eq 'D')
	{
		choiceD();
	} 
	elsif (uc($userInput) eq 'X')
	{
		choiceX();
	}
	else
	{
		print "Incorrect Input!\n";
	}
}

sub printMenu {
	print "MENU\n"; 
	print "===========================\n";
	print "(p, P) Print users info\n";
	print "(a, A) Add new user\n";
	print "(s, S) Search user\n";
	print "(d, D) Delete user\n";
	print "(x, X) Exit\n\n";
	print "Enter your choice: ";
}

sub choiceP {
	my $choice = $_[0];

	print "\nPriting Users\n";

	if(-e $fileLocation)
	{
		open(FILE, '<', $fileLocation) or die $!;
		my @fileContent = <FILE>;
		my $lineNumber = 1;

		foreach(@fileContent)
			{
				my @currentUser = split(/\t/, $_, 3);
				chomp(@currentUser);
				
				printf ("%-3s %-8s %-15s %-15s\n",$lineNumber++, $currentUser[0], $currentUser[1], $currentUser[2]);
			}
		print "\n";
		close FILE;
	}
	else 
	{ 
		print "There is no data file to print any users from!\n\n";
	}
}

sub choiceA {
	my $choice = $_[0];
	
	# Read File

	if (open(FILE, '+>>', $fileLocation) ne 1)
	{
		print "No current data found. Creating data file\n";
		close FILE;
	}
	else
	{
		print "Found existing file\n";
		our @fileContent = <FILE>;
		close FILE;
	} 

	print "\nAdding a user\n";
	print "First Name: ";
	my $firstName = uc(<STDIN>);
	print "Last Name: ";
	my $lastName = uc(<STDIN>);

	chomp($firstName);
	chomp($lastName);

	my @firstNameArray = split("", $firstName);
	my @lastNameArray = split("", $lastName);
	my $userName = $firstNameArray[0].$lastNameArray[0].$lastNameArray[1].$lastNameArray[2].$lastNameArray[3];

	foreach(@fileContent)
	{
		my @currentUser = split(/\t/, $_, 3);
		chomp(@currentUser);
		if ($currentUser[1] eq $firstName && $currentUser[2] eq $lastName)
		{
			print "Duplicate Users! Adding '1' to the username\n";
			$userName = $userName."1";
		}
	}

	my $addUser = $userName."\t".$firstName."\t".$lastName."\n";
	
	push(@fileContent, $addUser);
	@fileContent = sort(@fileContent);

	# Write File
	open(FILE, '>', $fileLocation) or die $!;
	foreach(@fileContent)
	{
		print FILE $_;
	}
	close FILE;
}


sub choiceS {
	my $choice = $_[0];

	print "\nSearching for a user\n";
	print "First Name: ";
	my $firstName = uc(<STDIN>);
	chomp($firstName);

	open(FILE, '<', $fileLocation);
	my @fileContent = <FILE>;

	foreach(@fileContent)
	{
		my @userFirstName = split(/\t/, $_, 3);
		if ($userFirstName[1] eq $firstName)
		{
			print "User Found!\n";
			print $userFirstName[0]."\t".$userFirstName[1]."\t".$userFirstName[2]."\n";
		}
	}


	close FILE;
}

sub choiceD {
	my $choice = $_[0];

	print "\nDeleting a user\n";
	print "First Name: ";
	my $firstName = uc(<STDIN>);

	my @usersBeforeDelete;

	chomp($firstName);

	# Read File
	open(FILE, '<', $fileLocation);
	my @fileContent = <FILE>;
	close FILE;

	my @tempArray = @fileContent;

	foreach(@fileContent)
	{
		my @userFirstName = split(/\t/, $_, 3);
		if ($userFirstName[1] ne $firstName)
		{
			push(@usersBeforeDelete, shift(@tempArray));
		}
		else
		{
			print "User Deleted\n";
			shift(@tempArray);
		}
	}

	push(@tempArray, @usersBeforeDelete);
	
	@fileContent = sort(@tempArray);

	# Write File
	open(FILE, '>', $fileLocation) or die $!;
	foreach(@fileContent)
	{
		print FILE $_;
	}
	close FILE;
}

sub choiceX {
	my $choice = $_[0];

	print "\nExiting Program\n";


	exit;
}
