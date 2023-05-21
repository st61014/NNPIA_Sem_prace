export interface JobListing {
    listingID: number
    jobField: string
    position: string
    pay: number
    listingPosterId: number
}

export interface AppUser {
    id: number
    currentWorkingField: string
    firstName: string
    lastName: string
    password: string
    username: string
}
export interface UsersInterestedInJob {
    job_listing_id: number
    user_id: number
    creation_date: string
    status: string
}

export interface JobsInterestedIn {
    job_field: string
    position: string
    pay: number
    creation_date: string
    status: string
}