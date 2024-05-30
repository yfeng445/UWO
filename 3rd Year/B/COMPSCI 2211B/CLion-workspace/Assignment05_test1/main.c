#include<stdio.h>
#include<stdlib.h>
struct student
{
    int ID;
    char name[32];
    struct student *next;
};
#define LEN sizeof(struct student)
struct student *add_link(struct student *head)
{
    struct student *temp = (struct student*)malloc(LEN);
    printf("input ID:\n");
    scanf("%d", &temp->ID);
    printf("input name:\n");
    scanf("%s", temp->name);
    temp->next = NULL;  //可以省略
    temp->next = head->next;
    head->next = temp;
    temp = NULL; //要置空，防止成为野指针
    return head;
}
struct student *delete_head(struct student *head)
{
    struct student *temp = head->next;
    head->next = temp->next;
    free(temp);
    temp = NULL;
    return head;
}
void show_link(struct student *head)
{
    struct student *p = head->next;
    printf("ID\tname\n");
    while(p != NULL)
    {
        printf("%d\t%s\n", p->ID, p->name);
        p = p->next;
    }
}
int main()
{
//创建链表
    struct student *head;
    head = (struct student *)malloc(LEN);
    head->next = NULL;
    int i = 0;
    for(i = 0; i < 5; i++)
    head = add_link(head);    //头插插入链表调用
    show_link(head);
    head = delete_head(head); //头删删除链表调用
    show_link(head);
    return 0;
}