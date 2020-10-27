package nl.wesselbarten.newsapp.presentation.news

sealed class RefreshTopHeadLinesAction {
    object Success : RefreshTopHeadLinesAction()
    class Error(val message: String) : RefreshTopHeadLinesAction()
}