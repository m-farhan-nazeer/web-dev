from django.urls import path

from . import views

#for multiple apps 
app_name = "polls"

# in index.html
# <li><a href="{% url 'polls:detail' question.id %}">{{ question.question_text }}</a></li>

urlpatterns = [
    # ex: /polls/
    path("", views.index, name="index"),
    # ex: /polls/5/
    path("<int:question_id>/", views.detail, name="detail"),
    # ex: /polls/5/results/
    path("<int:question_id>/results/", views.results, name="results"),
    # ex: /polls/5/vote/
    path("<int:question_id>/vote/", views.vote, name="vote"),
]