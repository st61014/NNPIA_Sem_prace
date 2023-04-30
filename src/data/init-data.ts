export interface Task {
    id: number
    title: string
    description: string
    creationDate: Date
    updateDate?: Date
    done: boolean
}

export const tasks = [
    {
        id: 1,
        title: "Finish TypeScript tutorial",
        description: "Complete the TypeScript tutorial on the official website",
        creationDate: new Date("2022-12-31T23:59:59Z"),
        updateDate: new Date("2023-01-01T00:00:01Z"),
        done: false
    },
    {
        id: 2,
        title: "Write a blog post",
        description: "Write a blog post about the benefits of TypeScript",
        creationDate: new Date("2023-02-28T12:00:00Z"),
        updateDate: new Date("2023-03-01T09:30:00Z"),
        done: false
    },
    {
        id: 3,
        title: "Fix bugs in project",
        description: "Resolve the outstanding bugs in the project before the deadline",
        creationDate: new Date("2023-03-15T16:00:00Z"),
        updateDate: new Date("2023-03-28T21:15:00Z"),
        done: false
    }
]