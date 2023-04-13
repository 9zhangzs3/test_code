import boto3
from sys import argv

def test(ak,sk):
    ec2 = boto3.client(
        'ec2',
        aws_access_key_id = ak,
        aws_secret_access_key = sk,
    )
    response = ec2.describe_instances()
    return response

if __name__ == '__main__':
    result = test(argv[1], argv[2])
    print(result)