# Notes to Reviewers

To the code reviewers:

The solution to the springboot-interview-test I implemented is available at https://github.com/ignasi35/adobe-bookstore-assignment
As you'll soon see, the code in the main branch is not complete. I am not a SpringBoot developer and spent a good amount of time getting at least a minimal set of features up and running.
I've left a few breadcrumbs as GitHub issues with a list of pending features, and also several improvements I would work on when taking this service to production.

I also left an [open PR](https://github.com/ignasi35/adobe-bookstore-assignment/pull/6) to demonstrate how I like to work:
 - there's a main PR where I create an [integration test](https://github.com/ignasi35/adobe-bookstore-assignment/pull/6)
 - there are other smaller PR's where I keep adding smaller parts of the feature I'm developing (e.g. https://github.com/ignasi35/adobe-bookstore-assignment/pull/10). These smaller PRs don't target the main branch but the branch of the parent PR.
This uses the outside-in approach of development, but it also promotes working in small PRs. As long as all contributors of the feature have access to the repo where the branch of the main PR (in this case https://github.com/ignasi35/adobe-bookstore-assignment/pull/6) is, then it's a high productivity pattern.

Summing up, if you are only interested on the code I could put together, then I suggest you look at https://github.com/ignasi35/adobe-bookstore-assignment/commits/add-integration-test-start-outside-in. If, OTOH, you want to know more about how I like to work and what I have in mind for how a service in production should look like, then https://github.com/ignasi35/adobe-bookstore-assignment/issues/14 or the whole list of issues labelled as enhancements is a good place to look.

Best regards,
