# FRC 7419 Training Repo
On Team 7419, all of our code lives in GitHub repositories just like this one. In the next couple of months, you guys are going to learn how to use GitHub effectively, so that we can all collaborate on robot code during the season without overriding each other's work. You're also going to do a number of training tasks so that you can get comfortable with writing code for FRC. 

### How to Use
There's a more detailed version of this on Notion, but before you start trying to use this repository, make sure you have Visual Studio Code and Sourcetree installed, and your GitHub account set up with Sourcetree. You're going to want to clone this repository into Sourcetree so you can edit the code from VSCode. At this stage, you should only ever commit to **your personal branch.**

### Branches Overview
`master` is pretty much just an empty code project. There's only one test in here, and it exists solely to make sure that the tests are running properly on your machine. If `master` does not build on your machine when you set up Sourcetree / VSCode for the first time, make sure you've followed all of the steps in the Notion guide. If you still can't figure it out, message Neha or Henry on Slack. 

`development` will be updated weekly with new unit tests to verify that your assignment for that week works. Warning: your code **will not build** if the tests don't pass. This is normal, so don't freak out -- it just means you need to keep working. The tests passing is how you know that the code you wrote should work. They're not catch-all, but because we don't have access to a robot right now, they're pretty good. We're also going to be looking at your code and checking in with you guys as we go.

Both `master` and `development` are protected, which means you can't commit directly to them, and for now, you shouldn't. The only branch you should ever commit to right now is your personal branch. Each of you is getting one with your name on it. Every time you make a unit test pass, commit. If you figure something out, commit. If you're done programming for the day, commit. They say commit early and commit often, which just gives you more to fall back on when you inevitably break something major and have to reverse a commit.

### Workflow

Every week, we're going to update the development branch. Every week, you're going to pull the development branch into your personal one. We'll show you how to do this when you have to. You're going to do your assignments, and keep committing to your personal branch til the code builds (which only happens when the tests pass). 
