# FRC 7419 Training Repo
On Team 7419, all of our code lives in GitHub repositories just like this one. In the next couple of months, you guys are going to learn how to use GitHub effectively, so that we can all collaborate on robot code during the season without overriding each other's work. You're also going to do a number of training tasks so that you can get comfortable with writing code for FRC. 

### How to Use
You're going to want to clone this repository into Sourcetree so you can edit the code from VSCode. At this stage, you should only ever commit to **your personal branch.** There is a more detailed explanation of this on our team Notion page.

### Branches Overview
`master` is an empty shell. The idea is that `master` will always work and it will be updated very rarely. There's only one test in here, and it exists solely to make sure that the tests are running properly on your machine. If `master` does not build on your machine when you set up Sourcetree / VSCode for the first time, make sure you've followed all of the steps in the Notion guide.

`development` will be updated weekly with new unit tests to verify that your assignment for that week works. Warning: if the tests don't pass, your code **will not build** This is normal, so don't freak out -- it just means you need to troubleshoot! The tests passing is how you know that the code you wrote should work. They're not perfect, but because we don't have access to a robot right now, they're the best we have to validate your code. Neha and I are always available through slack, and we'll hopefully check in on you guys weekly.

Both `master` and `development` are protected, which means you can't commit directly to them. The only branch you should ever commit to (for now) is your personal branch. Each of you is getting a branch with your name. The best tip we can give you is to COMMIT! You wrote one line? Commit it. It will seriously save you so, so much time down the road. Commit, commit, commit.

### Workflow

Every week, we're going to update `development`. Every week, you're going to **pull** the development branch into your personal one. This will basically update your code. You **never** want to be any commits behind. We'll show you how to do this. You're going to do your assignments, and keep committing to your personal branch.
